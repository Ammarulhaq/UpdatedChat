
package com.example.conversationlisting.modelclasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.conversationlisting.modelclasses.chatMessages.Message;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConversationsItem implements Parcelable {

    @SerializedName("conversation_id")
    @Expose
    private Integer conversationId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("conversation_icon")
    @Expose
    private Object conversationIcon;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("totalmessages")
    @Expose
    private Integer totalmessages;
    @SerializedName("unreadmessages")
    @Expose
    private Integer unreadmessages;
    @SerializedName("latest_message")
    @Expose
    private Message latestMessage;
    @SerializedName("latest_date")
    @Expose
    private String latestDate;
    @SerializedName("is_fvrt")
    @Expose
    private Boolean isFvrt;

    protected ConversationsItem(Parcel in) {
        if (in.readByte() == 0) {
            conversationId = null;
        } else {
            conversationId = in.readInt();
        }
        name = in.readString();
        createdDate = in.readString();
        if (in.readByte() == 0) {
            totalmessages = null;
        } else {
            totalmessages = in.readInt();
        }
        if (in.readByte() == 0) {
            unreadmessages = null;
        } else {
            unreadmessages = in.readInt();
        }
        latestDate = in.readString();
        byte tmpIsFvrt = in.readByte();
        isFvrt = tmpIsFvrt == 0 ? null : tmpIsFvrt == 1;
    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        if (conversationId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(conversationId);
//        }
//        dest.writeString(name);
//        dest.writeString(createdDate);
//        if (totalmessages == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(totalmessages);
//        }
//        if (unreadmessages == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(unreadmessages);
//        }
//        dest.writeString(latestDate);
//        dest.writeByte((byte) (isFvrt == null ? 0 : isFvrt ? 1 : 2));
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<ConversationsItem> CREATOR = new Creator<ConversationsItem>() {
//        @Override
//        public ConversationsItem createFromParcel(Parcel in) {
//            return new ConversationsItem(in);
//        }
//
//        @Override
//        public ConversationsItem[] newArray(int size) {
//            return new ConversationsItem[size];
//        }
//    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (conversationId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(conversationId);
        }
        dest.writeString(name);
        dest.writeString(createdDate);
        if (totalmessages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalmessages);
        }
        if (unreadmessages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(unreadmessages);
        }
        if (latestMessage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeParcelable(latestMessage,1);
        }

        dest.writeString(latestDate);
        dest.writeByte((byte) (isFvrt == null ? 0 : isFvrt ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConversationsItem> CREATOR = new Creator<ConversationsItem>() {
        @Override
        public ConversationsItem createFromParcel(Parcel in) {
            return new ConversationsItem(in);
        }

        @Override
        public ConversationsItem[] newArray(int size) {
            return new ConversationsItem[size];
        }
    };

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getConversationIcon() {
        return conversationIcon;
    }

    public void setConversationIcon(Object conversationIcon) {
        this.conversationIcon = conversationIcon;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getTotalmessages() {
        return totalmessages;
    }

    public void setTotalmessages(Integer totalmessages) {
        this.totalmessages = totalmessages;
    }

    public Integer getUnreadmessages() {
        return unreadmessages;
    }

    public void setUnreadmessages(Integer unreadmessages) {
        this.unreadmessages = unreadmessages;
    }

    public Message getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(Message latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(String latestDate) {
        this.latestDate = latestDate;
    }

    public Boolean getIsFvrt() {
        return isFvrt;
    }


    public void setIsFvrt(Boolean isFvrt) {
        this.isFvrt = isFvrt;
    }

}
