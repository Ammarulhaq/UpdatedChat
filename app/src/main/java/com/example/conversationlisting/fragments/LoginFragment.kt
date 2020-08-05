package com.example.conversationlisting.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.conversationlisting.R
import com.example.conversationlisting.adapters.ConversationListingAdapter
import com.example.conversationlisting.interfaces.notifyActivity
import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.modelclasses.LoginModel.Company
import com.example.conversationlisting.modelclasses.LoginModel.Datum
import com.example.conversationlisting.modelclasses.LoginModel.LoginDetails
import com.example.conversationlisting.modelclasses.LoginModel.Resource
import com.example.conversationlisting.retrofitservices.CustomCallback
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse
import com.example.conversationlisting.webservicesinterfaceshandlers.WebServicesHandler
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.login_screen.*
import java.util.regex.Pattern


class LoginFragment() : Fragment() , ConversationListingAdapter.OnConversationSelect{

    var listCompanies:List<Datum>?=null
    var notified:notifyActivity?=null
    val passwordPattern:Pattern= Pattern.compile("^"+".{6,}")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        notified=context as notifyActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_screen,container,false)
    }

    override fun onStart() {
        super.onStart()


        usrPassword.setOnFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if (hasFocus) {
                    userCompanies.adapter=null
                    companiesLoader.visibility=View.VISIBLE
                     Fetch_Companies()
                }
            }
        })

        usrLogin.setOnClickListener {

            if(!ValidateEmail() || !ValidatePassword())
            {
                    return@setOnClickListener
            }
            val company: Datum = userCompanies.selectedItem as Datum
            GetLoginDetails(userName.text.toString(),usrPassword.text.toString(),company.companyId);
        }


    }
    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onSelectConversation(position: Int, conversationsItem: ConversationsItem?) {
        TODO("Not yet implemented")
    }

    fun GetCompaniesDetails(Name:String)
    {

        WebServicesHandler.instance.login(Name,object : CustomCallback<RetrofitJSONResponse?>() {
            override fun onSuccess(response: RetrofitJSONResponse) {

               listCompanies= Gson().fromJson(response.optJSONArray("data").toString(), Array<Datum>::class.java).toList()

                var adpt=ArrayAdapter<Datum>(context!!,android.R.layout.simple_spinner_item, listCompanies!!)
                adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                userCompanies.adapter=adpt

                companiesLoader.visibility=View.GONE
            }

            override fun onFailure(
                completed: Boolean,
                response: RetrofitJSONResponse?,
                ex: Exception?
            ) {
                Toast.makeText(context,"Not Regitered in company",Toast.LENGTH_SHORT).show()
            }


        });

    }

    fun GetLoginDetails(Name:String,Password:String,CompanyID:Int)
    {

        WebServicesHandler.instance.login(Name,Password,CompanyID,object : CustomCallback<RetrofitJSONResponse?>()
        {
            override fun onSuccess(response: RetrofitJSONResponse) {
                val log=LoginDetails()

                log.token=response.get("token").toString()
              //  log.companies=response.get("companies") as List<Company>
                log.resource=Gson().fromJson(JsonParser().parse(response.get("resource").toString()),Resource::class.java)

                notified!!.onSuccessfullLogin(log)

            }

            override fun onFailure(
                completed: Boolean,
                response: RetrofitJSONResponse?,
                ex: java.lang.Exception?
            ) {
               Toast.makeText(context,"username/password incorrect",Toast.LENGTH_SHORT).show()
            }

        })


    }
    fun Fetch_Companies()
    {
        if(!ValidateEmail())
        {
            userCompanies.adapter=null
            return;
        }
        GetCompaniesDetails(userName.text.toString())
    }

    fun ValidateEmail():Boolean
    {
        if(userName.text.toString().trim().isEmpty())
        {

            userName.setError("Email Field must not be empty")
            return false
        }
        else
        {
            userName.setError(null)
            return true
        }

    }

    fun ValidatePassword():Boolean
    {

        if(usrPassword.text.toString().trim().isEmpty())
        {
            usrPassword.setError("Password field can't be empty")
            return false;
        }
        else if(!passwordPattern.matcher(usrPassword.text.toString().trim()).matches())
        {
            usrPassword.setError("Password required min length 6")
            return false;
        }
        else
        {
            usrPassword.setError(null)
            return true
        }


    }

}
