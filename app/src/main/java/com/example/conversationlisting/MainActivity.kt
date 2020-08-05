package com.example.conversationlisting

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.conversationlisting.SocketFolder.SocketManager
import com.example.conversationlisting.fragments.ChatFragment
import com.example.conversationlisting.fragments.ConversationFragment
import com.example.conversationlisting.fragments.LoginFragment
import com.example.conversationlisting.interfaces.notifyActivity
import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.modelclasses.LoginModel.LoginDetails
import com.example.conversationlisting.utilities.SharedPreference



class MainActivity : AppCompatActivity(),notifyActivity{

var userDetails:LoginDetails?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar= findViewById<androidx.appcompat.widget.Toolbar>(R.id.Strip)
        setSupportActionBar(toolbar)
        showFragment(LoginFragment())
    }
    fun  showFragment(frag:Fragment)
    {
        val trans= supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragmentHolder,frag)
        trans.addToBackStack(null)
        trans.commit()
    }

    override fun onConversationFragment(position: Int, conversationsItem: ConversationsItem) {




        val trans= supportFragmentManager.beginTransaction()
        val frag2= ChatFragment()

        val args = Bundle()
        // Send string data as key value format
        args.putParcelable("latestMessage",conversationsItem.latestMessage)
        args.putParcelable("conversationItem",conversationsItem)
        frag2.arguments=args
        val spref= SharedPreference(this)
        trans.replace(R.id.fragmentHolder,frag2)
        trans.addToBackStack("conversationListing")
        trans.commit()
    }

    override fun onSuccessfullLogin(myuserDetails:LoginDetails) {


        val trans= supportFragmentManager.beginTransaction()
        val frag2= ConversationFragment()
        userDetails=myuserDetails

        val spref= SharedPreference(this)
        spref.save("token", userDetails!!.token)
        spref.save("resourceId",userDetails!!.resource.resourceId)
        val args = Bundle()
        // Send string data as key value format
        args.putString("token",userDetails!!.token)
        frag2.arguments=args
        trans.replace(R.id.fragmentHolder,frag2)
        trans.addToBackStack(null)
        trans.commit()

    }

    override fun backPress() {

        supportFragmentManager.popBackStack("conversationListing",FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}

