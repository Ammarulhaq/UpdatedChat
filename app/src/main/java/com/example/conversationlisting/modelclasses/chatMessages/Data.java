
package com.example.conversationlisting.modelclasses.chatMessages;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("remainingMessages")
    @Expose
    private Integer remainingMessages;
    @SerializedName("messages")
    @Expose
    private List<Message> messages = new ArrayList<Message>();

    public Integer getRemainingMessages() {
        return remainingMessages;
    }

    public void setRemainingMessages(Integer remainingMessages) {
        this.remainingMessages = remainingMessages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}
