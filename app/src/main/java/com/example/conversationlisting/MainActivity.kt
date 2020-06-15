package com.example.conversationlisting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.conversationlisting.SocketFolder.SocketManager
import com.example.conversationlisting.fragments.ConversationFragment
import com.example.conversationlisting.modelclasses.ConversationsItem

class MainActivity : AppCompatActivity(), ConversationFragment.notifyActivity{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






         showFragment1()
        
    }


    fun showFragment1()
    {

        val trans= supportFragmentManager.beginTransaction()
        val frag1= ConversationFragment()
        trans.replace(R.id.fragmentHolder,frag1)
        trans.addToBackStack(null)
        trans.commit()
    }


    override fun onFragmentClicked(position: Int, conversationsItem: ConversationsItem) {
        var intent:Intent=Intent(this, ChatScreen::class.java)
        intent.putExtra("latestMessage",conversationsItem.latestMessage)
        intent.putExtra("conversationItem", conversationsItem)

        startActivity(intent)
    }
}

