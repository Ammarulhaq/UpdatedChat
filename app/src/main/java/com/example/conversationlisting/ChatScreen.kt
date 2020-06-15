package com.example.conversationlisting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conversationlisting.SocketFolder.SocketManager
import com.example.conversationlisting.adapters.chatListingAdapter
import com.example.conversationlisting.fragments.ConversationFragment
import com.example.conversationlisting.interfaces.OnSocketConnectionListener
import com.example.conversationlisting.interfaces.SocketEvents
import com.example.conversationlisting.interfaces.SocketResponseIds
import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.modelclasses.LatestMessage
import com.example.conversationlisting.modelclasses.chatMessages.Data
import com.example.conversationlisting.modelclasses.chatMessages.Message
import com.example.conversationlisting.retrofitservices.CustomCallback
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse
import com.example.conversationlisting.webservicesinterfaceshandlers.WebServicesHandler
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat_screen.*
import org.json.JSONObject


class ChatScreen : AppCompatActivity(),OnSocketConnectionListener{

    var conversationItem:ConversationsItem?=null
    var latestMessage:Message?=null
    var madapter: chatListingAdapter?=null
    var ListMessages:Data?= Data()
    var bringMessages:Int=10
    var isScrolling=false
    var currentItems=0;var totalItems=0;var scrollOutItems=0

    var socket=SocketManager.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_screen)

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        socket.connectSocket()


        backArrow.setOnClickListener {
            var intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

            finish()

        }

        latestMessage=intent.getParcelableExtra("latestMessage")
        conversationItem = intent.getParcelableExtra("conversationItem")

        conversationTitle.text=conversationItem!!.name
        if(conversationItem!!.conversationIcon!=null)
        {
            Picasso.with(this@ChatScreen).load(conversationItem!!.conversationIcon.toString()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(userChatImage,object:com.squareup.picasso.Callback
            {
                override fun onSuccess() {
                    Log.d("Success","Image Inserted")
                }

                override fun onError() {
                    Log.d("Error","SomeError Occured")
                }

            })
        }

        madapter= chatListingAdapter(this@ChatScreen,ListMessages)
        chatListing.adapter=madapter

        chatListing.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                currentItems =
                    (chatListing.layoutManager as LinearLayoutManager).childCount
                totalItems =
                    (chatListing.layoutManager as LinearLayoutManager).itemCount
                scrollOutItems =
                    (chatListing.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()


                if (isScrolling && (totalItems == (scrollOutItems + currentItems))) {
                    isScrolling = false

                    if(ListMessages!!.remainingMessages > 0)
                    {

                        if(ListMessages!!.remainingMessages < 5)
                        {
                            LoadMoreMessages(conversationItem!!,bringMessages+ListMessages!!.remainingMessages)
                        }
                        else
                        {
                            bringMessages=bringMessages+5
                            LoadMoreMessages(conversationItem!!,bringMessages+5)
                        }
                    }
                }
            }
        })
        LoadMoreMessages(conversationItem!!,bringMessages)

        chatMsg.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER && ListMessages!=null) {
                    // Perform action on key press

                    val payload = JSONObject()

                    val msg= chatMsg.text
                    payload.put("message",msg.toString())
                    payload.put("resource_id", 132)
                    payload.put("conversation_id", conversationItem!!.conversationId)
                    payload.put("company_id",188)
                    payload.put("message_type",0)
                    payload.put("reply_id",0)



                    socket.socket.emit(SocketEvents.message,payload)

                    socket.socket.on(SocketResponseIds.message,object:Emitter.Listener
                    {

                        override fun call(vararg args: Any?) {

                            val rplydata=Gson().fromJson(args[0].toString(),Message::class.java)
                            rplydata.resourceId
                            ListMessages?.messages?.add(0,rplydata)
                            this@ChatScreen.runOnUiThread{

                                chatListing.adapter!!.notifyDataSetChanged()
                                chatMsg.text.clear()
                            }
                            socket.socket.off()
                        }

                    })

                    return true
                }
                return false
            }
        })

    }

    fun LoadMoreMessages(conversationsItem: ConversationsItem,downloadMsgs:Int)
    {


        WebServicesHandler.instance.loadMoreMessages("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55X2lkIjoiMTg4IiwiZW1haWwiOiJoYW1tYWRAd3VycWkuY29tIiwicmVzb3VyY2VfaWQiOjEzMiwiZXhwIjoxNTkyODE1OTYyfQ.ablSga3SyaV7YESEMwAfi0U23nnGHaWTDtRfgEwUDrE",conversationsItem.conversationId,latestMessage!!.conversationMessageId,downloadMsgs,object : CustomCallback<RetrofitJSONResponse?>()
        {

            override fun onSuccess(response: RetrofitJSONResponse) {



                val ans=response.optJSONObject("data")
                val data=Gson().fromJson(ans.toString(),Data::class.java)

                ListMessages?.messages?.addAll(data.messages)
                ListMessages?.messages?.add(0,latestMessage)
                ListMessages?.remainingMessages=data.remainingMessages

                chatListing.adapter!!.notifyDataSetChanged()

            }

            override fun onFailure(
                completed: Boolean,
                response: RetrofitJSONResponse?,
                ex: Exception?
            ) {
              Log.d("Exception",ex.toString())
            }
        })

    }
    fun showFragment1()
    {

        val trans= supportFragmentManager.beginTransaction()
        val frag1= ConversationFragment()
        trans.replace(R.id.fragmentHolder,frag1)
        trans.addToBackStack(null)
        trans.commit()
    }

    override fun onSocketEventFailed() {
        TODO("Not yet implemented")
    }

    override fun onSocketConnectionStateChange(socketState: Int) {
        TODO("Not yet implemented")
    }

    override fun onInternetConnectionStateChange(socketState: Int) {
        TODO("Not yet implemented")
    }
}