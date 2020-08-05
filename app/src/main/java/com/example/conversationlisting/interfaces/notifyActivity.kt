package com.example.conversationlisting.interfaces

import com.example.conversationlisting.modelclasses.ConversationsItem
import com.example.conversationlisting.modelclasses.LoginModel.LoginDetails

public interface notifyActivity
{
    fun onConversationFragment(position: Int,conversationModel: ConversationsItem)

    fun onSuccessfullLogin(userDetails:LoginDetails)

    fun backPress()
}