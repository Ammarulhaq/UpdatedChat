package com.example.conversationlisting.interfaces

interface SocketResponseIds {
    companion object {
        const val activeConversationDetail = "activeConversationDetail"
        const val conversationDetail = "conversationDetail"
        const val joinedConversation = "joinedConversation"
        const val messageUpdated = "messageUpdated"
        const val messageDeleted = "messageDeleted"
        const val filteredMessageIDs = "filteredMessageIDs"
        const val unreadConversations = "unreadConversations"
        const val updatePendingTick = "updatePendingTick"
        const val removeAttachment = "removeAttachment"
        const val associationAndAttachmentCount = "associationAndAttachmentCount"
        const val linkedActivitiesCount = "linkedActivitiesCount"
        const val unreadConvCount = "unreadConvCount"
        const val private_conversation = "private_conversation"
        const val usersRecording = "usersRecording"
        const val onlineUsers = "onlineUsers"
        const val usersTyping = "usersTyping"
        const val message = "message"
    }
}