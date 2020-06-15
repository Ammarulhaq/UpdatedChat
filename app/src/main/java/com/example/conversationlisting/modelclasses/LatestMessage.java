
package com.example.conversationlisting.modelclasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestMessage implements Parcelable {

    @SerializedName("conversation_message_id")
    @Expose
    private Integer conversationMessageId;
    @SerializedName("conversation_id")
    @Expose
    private Integer conversationId;
    @SerializedName("resource_id")
    @Expose
    private Integer resourceId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("sent_datetime")
    @Expose
    private String sentDatetime;
    @SerializedName("message_type")
    @Expose
    private Integer messageType;
    @SerializedName("is_deleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("reply_id")
    @Expose
    private Object replyId;
    @SerializedName("filename")
    @Expose
    private Object filename;
    @SerializedName("directory_name")
    @Expose
    private Object directoryName;
    @SerializedName("message_attachment_id")
    @Expose
    private Object messageAttachmentId;
    @SerializedName("message_id")
    @Expose
    private Integer messageId;
    @SerializedName("resource_image")
    @Expose
    private String resourceImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("read_by_all")
    @Expose
    private Boolean readByAll;
    @SerializedName("messageattachment_id")
    @Expose
    private Integer messageattachmentId;

    protected LatestMessage(Parcel in) {
        if (in.readByte() == 0) {
            conversationMessageId = null;
        } else {
            conversationMessageId = in.readInt();
        }
        if (in.readByte() == 0) {
            conversationId = null;
        } else {
            conversationId = in.readInt();
        }
        if (in.readByte() == 0) {
            resourceId = null;
        } else {
            resourceId = in.readInt();
        }
        message = in.readString();
        sentDatetime = in.readString();
        if (in.readByte() == 0) {
            messageType = null;
        } else {
            messageType = in.readInt();
        }
        byte tmpIsDeleted = in.readByte();
        isDeleted = tmpIsDeleted == 0 ? null : tmpIsDeleted == 1;
        if (in.readByte() == 0) {
            messageId = null;
        } else {
            messageId = in.readInt();
        }
        resourceImage = in.readString();
        displayName = in.readString();
        byte tmpReadByAll = in.readByte();
        readByAll = tmpReadByAll == 0 ? null : tmpReadByAll == 1;
        if (in.readByte() == 0) {
            messageattachmentId = null;
        } else {
            messageattachmentId = in.readInt();
        }
    }

//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        if (conversationMessageId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(conversationMessageId);
//        }
//        if (conversationId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(conversationId);
//        }
//        if (resourceId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(resourceId);
//        }
//        dest.writeString(message);
//        dest.writeString(sentDatetime);
//        if (messageType == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(messageType);
//        }
//        dest.writeByte((byte) (isDeleted == null ? 0 : isDeleted ? 1 : 2));
//        if (messageId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(messageId);
//        }
//        dest.writeString(resourceImage);
//        dest.writeString(displayName);
//        dest.writeByte((byte) (readByAll == null ? 0 : readByAll ? 1 : 2));
//        if (messageattachmentId == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(messageattachmentId);
//        }
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    public static final Creator<LatestMessage> CREATOR = new Creator<LatestMessage>() {
//        @Override
//        public LatestMessage createFromParcel(Parcel in) {
//            return new LatestMessage(in);
//        }
//
//        @Override
//        public LatestMessage[] newArray(int size) {
//            return new LatestMessage[size];
//        }
//    };

    public static final Creator<LatestMessage> CREATOR = new Creator<LatestMessage>() {
        @Override
        public LatestMessage createFromParcel(Parcel in) {
            return new LatestMessage(in);
        }

        @Override
        public LatestMessage[] newArray(int size) {
            return new LatestMessage[size];
        }
    };

    public Integer getConversationMessageId() {
        return conversationMessageId;
    }

    public void setConversationMessageId(Integer conversationMessageId) {
        this.conversationMessageId = conversationMessageId;
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentDatetime() {
        return sentDatetime;
    }

    public void setSentDatetime(String sentDatetime) {
        this.sentDatetime = sentDatetime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Object getReplyId() {
        return replyId;
    }

    public void setReplyId(Object replyId) {
        this.replyId = replyId;
    }

    public Object getFilename() {
        return filename;
    }

    public void setFilename(Object filename) {
        this.filename = filename;
    }

    public Object getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(Object directoryName) {
        this.directoryName = directoryName;
    }

    public Object getMessageAttachmentId() {
        return messageAttachmentId;
    }

    public void setMessageAttachmentId(Object messageAttachmentId) {
        this.messageAttachmentId = messageAttachmentId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getResourceImage() {
        return resourceImage;
    }

    public void setResourceImage(String resourceImage) {
        this.resourceImage = resourceImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getReadByAll() {
        return readByAll;
    }

    public void setReadByAll(Boolean readByAll) {
        this.readByAll = readByAll;
    }

    public Integer getMessageattachmentId() {
        return messageattachmentId;
    }

    public void setMessageattachmentId(Integer messageattachmentId) {
        this.messageattachmentId = messageattachmentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (conversationMessageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(conversationMessageId);
        }
        if (conversationId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(conversationId);
        }
        if (resourceId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(resourceId);
        }
        dest.writeString(message);
        dest.writeString(sentDatetime);
        if (messageType == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(messageType);
        }
        dest.writeByte((byte) (isDeleted == null ? 0 : isDeleted ? 1 : 2));
        if (messageId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(messageId);
        }
        dest.writeString(resourceImage);
        dest.writeString(displayName);
        dest.writeByte((byte) (readByAll == null ? 0 : readByAll ? 1 : 2));
        if (messageattachmentId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(messageattachmentId);
        }
    }
}
