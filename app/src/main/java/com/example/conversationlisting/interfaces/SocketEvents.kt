package com.example.conversationlisting.interfaces

interface SocketEvents {
    companion object {
        const val conversation_id = "conversation_id"
        const val getActiveConversationDetail = "getActiveConversationDetail"
        const val newConversation = "newConversation"
        const val message = "message"
        const val updateMessage = "updateMessage"
        const val deleteMessage = "deleteMessage"
        const val searchMessages = "searchMessages"
        const val markRead = "markRead"
        const val attachmentRemoved = "attachmentRemoved"
        const val getAttachmentAndAssociationCount = "getAttachmentAndAssociationCount"
        const val unreadCount = "unreadCount"
        const val unreadCountConversation = "unreadCountConversation"
        const val cancelUpload = "cancelUpload"
        const val make_conversation_private = "make_conversation_private"
        const val isRecording = "isRecording"
        const val removeRecorder = "removeRecorder"
        const val getOnlineUsers = "getOnlineUsers"
        const val onlineUsers = "onlineUsers"
        const val getConversationTypists = "getConversationTypists"
        const val isTyping = "isTyping"
        const val removeTypist = "removeTypist"
    }
}