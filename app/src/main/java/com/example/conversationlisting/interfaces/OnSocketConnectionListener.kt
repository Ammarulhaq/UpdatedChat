package com.example.conversationlisting.interfaces

interface OnSocketConnectionListener {
    fun onSocketEventFailed()
    fun onSocketConnectionStateChange(socketState: Int)
    fun onInternetConnectionStateChange(socketState: Int)
}