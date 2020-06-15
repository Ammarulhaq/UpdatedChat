
package com.example.conversationlisting.modelclasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConversationDetails {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("data")
    @Expose
    private List<ConversationsItem> data = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ConversationsItem> getData() {
        return data;
    }

    public void setData(List<ConversationsItem> data) {
        this.data = data;
    }

}
