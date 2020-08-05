package com.example.conversationlisting.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conversationlisting.R
import com.example.conversationlisting.SocketFolder.SocketManager
import com.example.conversationlisting.adapters.chatListingAdapter
import com.example.conversationlisting.interfaces.SocketEvents
import com.example.conversationlisting.interfaces.SocketResponseIds
import com.example.conversationlisting.interfaces.notifyActivity
import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.modelclasses.chatMessages.Data
import com.example.conversationlisting.modelclasses.chatMessages.Message
import com.example.conversationlisting.retrofitservices.CustomCallback
import com.example.conversationlisting.retrofitservices.RetrofitJSONResponse
import com.example.conversationlisting.utilities.SharedPreference
import com.example.conversationlisting.webservicesinterfaceshandlers.WebServicesHandler
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_chat_screen.*
import kotlinx.android.synthetic.main.chattoolbar.*
import org.json.JSONObject


class ChatFragment : Fragment() {

    var conversationItem: ConversationsItem?=null
    var latestMessage: Message?=null
    var madapter: chatListingAdapter?=null
    var ListMessages: Data?= Data()
    var bringMessages:Int=8
    var userToken:String?=null
    var resourceId:Int?=null
    var isScrolling=false
    var currentItems=0;var totalItems=0;var scrollOutItems=0
    var socket= SocketManager.getInstance()
    var notify:notifyActivity?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        notify=context as notifyActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.activity_chat_screen, container, false)

        latestMessage=arguments!!.getParcelable("latestMessage")
        conversationItem=arguments!!.getParcelable("conversationItem")

        return view
    }

    override fun onStart() {
        super.onStart()
        activity!!.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        socket.connectSocket()


        backArrow.setOnClickListener {
            notify!!.backPress()

        }

        val sharedPreference: SharedPreference = SharedPreference(context!!)

        userToken=sharedPreference.getValueString("token")
        resourceId=sharedPreference.getValueInt("resourceId")
//        latestMessage=intent.getParcelableExtra("latestMessage")
//        conversationItem = intent.getParcelableExtra("conversationItem")


        conversationTitle.text=conversationItem!!.name
        if(conversationItem!!.conversationIcon!=null)
        {
            Picasso.with(context!!).load(conversationItem!!.conversationIcon.toString()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(userChatImage,object:com.squareup.picasso.Callback
            {
                override fun onSuccess() {
                    Log.d("Success","Image Inserted")
                }

                override fun onError() {
                    Log.d("Error","SomeError Occured")
                }

            })
        }

        madapter= chatListingAdapter(context!!,ListMessages)
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

                        if(ListMessages!!.remainingMessages < 8)
                        {
                            LoadMoreMessages(conversationItem!!,bringMessages+ListMessages!!.remainingMessages)
                        }
                        else
                        {


                            bringMessages=bringMessages+8
                            LoadMoreMessages(conversationItem!!,bringMessages)
                        }
                    }
                }
            }
        })
        LoadMoreMessages(conversationItem!!,bringMessages)

       chatMsg.addTextChangedListener(object:TextWatcher{
           override fun afterTextChanged(s: Editable?) {


           }

           override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

           }

           override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {



               sndmsg.background=null
               if(s!!.count()==0)
               {
                   sndmsg.setImageResource(R.drawable.recording_icon)
               }
               else
               {
                   sndmsg.setImageResource(R.drawable.send_blue_icon)

               }
           }


       })



        sndmsg.setOnClickListener {

            val payload = JSONObject()

            val msg = chatMsg.text
            payload.put("message", msg.toString())
            payload.put("resource_id", resourceId.toString())
            payload.put("conversation_id", conversationItem!!.conversationId)
            payload.put("company_id", 188)
            payload.put("message_type", 0)



            if (msg!!.isBlank())
                return@setOnClickListener;
            socket.socket.emit(SocketEvents.message, payload)

            socket.socket.on(SocketResponseIds.message, object : Emitter.Listener {

                override fun call(vararg args: Any?) {

                    val rplydata = Gson().fromJson(args[0].toString(), Message::class.java)

                    ListMessages?.messages?.add(0, rplydata)
                    activity!!.runOnUiThread {

                        chatListing.adapter!!.notifyDataSetChanged()

                        chatMsg.text!!.clear()
                    }
                    socket.socket.off()
                }

            })
        }


    }



    fun LoadMoreMessages(conversationsItem: ConversationsItem,downloadMsgs:Int)
    {


        WebServicesHandler.instance.loadMoreMessages(userToken!!,conversationsItem.conversationId,latestMessage!!.conversationMessageId,downloadMsgs,object : CustomCallback<RetrofitJSONResponse?>()
        {

            override fun onSuccess(response: RetrofitJSONResponse) {



                val ans=response.optJSONObject("data")
                val data=Gson().fromJson(ans.toString(),Data::class.java)
                ListMessages?.messages?.clear()
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




}