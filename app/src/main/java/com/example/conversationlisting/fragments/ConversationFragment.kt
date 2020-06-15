package com.example.conversationlisting.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conversationlisting.R
import com.example.conversationlisting.adapters.ConversationListingAdapter
import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.retrofitservices.CustomCallback
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse
import com.example.conversationlisting.webservicesinterfaceshandlers.WebServicesHandler
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragmentone.*
import java.lang.Exception

class ConversationFragment() : Fragment() , ConversationListingAdapter.OnConversationSelect{

    var ListConvo:List<ConversationsItem>?=null
    var isScrolling=false
    var currentItems=0;var totalItems=0;var scrollOutItems=0
    var madapter: ConversationListingAdapter?=null
    var notify:notifyActivity?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notify=context as notifyActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmentone,container,false)
    }

    override fun onStart() {
        super.onStart()
        GetAllConversations()

        searchConversation.addTextChangedListener(object: TextWatcher
        {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
             filterConversation(s.toString())
            }

        })
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

    fun GetAllConversations()
    {
        WebServicesHandler.instance.getAllConversations("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55X2lkIjoiMTg4IiwiZW1haWwiOiJoYW1tYWRAd3VycWkuY29tIiwicmVzb3VyY2VfaWQiOjEzMiwiZXhwIjoxNTkyODE1OTYyfQ.ablSga3SyaV7YESEMwAfi0U23nnGHaWTDtRfgEwUDrE",object : CustomCallback<RetrofitJSONResponse?>() {
            override fun onSuccess(response: RetrofitJSONResponse) {

                ListConvo = Gson().fromJson(
                    response.optJSONArray("data").toString(),
                    Array<ConversationsItem>::class.java
                ).toList()
                madapter =
                    ConversationListingAdapter(
                        context,ListConvo,this@ConversationFragment
                    )
                conversationListing.adapter = madapter
                conversationListing.layoutManager = LinearLayoutManager(context)

                conversationListing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)

                        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                            isScrolling = true
                        }
                    }

                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        currentItems = ( conversationListing.layoutManager as LinearLayoutManager).childCount
                        totalItems = ( conversationListing.layoutManager as LinearLayoutManager).itemCount
                        scrollOutItems =
                            ( conversationListing.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (isScrolling && (totalItems == (scrollOutItems + currentItems))) {
                            isScrolling = false
                            fetchMoreData()
                        }
                    }
                })
            }

            override fun onFailure(
                completed: Boolean,
                response: RetrofitJSONResponse?,
                ex: Exception?
            ) {
              Log.d("Faillure","Error Occured")
            }
        })
    }

    fun filterConversation(query:String)
    {
        var temp=ListConvo!!.toMutableList()
        temp.clear()
        for(list in ListConvo!!)
        {
            if(list.name.toLowerCase().contains(query.toLowerCase()))
            {
                temp.add(list)

            }
        }
        madapter?.filterList(temp)

    }

    private fun fetchMoreData() {
       loadingConversation.visibility= View.VISIBLE
        Handler().postDelayed({
            for(i in 1..5)
            {
                conversationListing.adapter?.notifyDataSetChanged()
               loadingConversation.visibility= View.GONE
            }
        },3000)
    }

    override fun onSelectConversation(position: Int,conversationModel:ConversationsItem) {
            notify?.onFragmentClicked(position,conversationModel)
    }

  public interface notifyActivity
  {
      fun onFragmentClicked(position: Int,conversationModel:ConversationsItem);
  }
}