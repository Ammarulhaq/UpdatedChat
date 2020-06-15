package com.example.conversationlisting.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conversationlisting.R;
import com.example.conversationlisting.modelclasses.ConversationsItem;
import com.example.conversationlisting.utilities.DateFormatConverter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ConversationListingAdapter extends RecyclerView.Adapter<ConversationListingAdapter.VHolder> {
    private Context context;
    private List<ConversationsItem> items;
    private OnConversationSelect onConversationSelect;

    public ConversationListingAdapter(Context context, List<ConversationsItem> items,OnConversationSelect onConversationSelect) {
        this.context = context;
        this.items = items;
        this.onConversationSelect=onConversationSelect;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.conversationtile, parent, false);
        return new VHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        holder.conversationTitle.setText(items.get(position).getName());
        holder.conversationInitDate.setText(DateFormatConverter.Companion.GetDate(items.get(position).getLatestDate()));
        Picasso.with(context).load(items.get(position).getLatestMessage().getResourceImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ownerImage, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                Log.d("Success","Image Loaded Successfully");
            }

            @Override
            public void onError() {
                Log.d("Error","Problem Occured while Loading Image");

            }
        });
        holder.latestMsg.setText(items.get(position).getLatestMessage().getMessage());
        holder.unreadMsg.setText(items.get(position).getUnreadmessages() <11 ? items.get(position).getUnreadmessages().toString() : "10+");
        holder.unreadMsg.setVisibility(items.get(position).getUnreadmessages() >0 ? View.VISIBLE: View.INVISIBLE);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }
    public void filterList(List<ConversationsItem> filter)
    {
        items=filter;
        notifyDataSetChanged();

    }

    public class VHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView conversationTitle,conversationInitDate,latestMsg,unreadMsg;
        ImageView ownerImage;
        public VHolder(@NonNull View itemView) {
            super(itemView);

            conversationTitle=itemView.findViewById(R.id.conversationTitle);
            ownerImage=itemView.findViewById(R.id.ownerConversationImage);
            conversationInitDate=itemView.findViewById(R.id.conversationStartDate);
            latestMsg=itemView.findViewById(R.id.latestMsg);
            unreadMsg=itemView.findViewById(R.id.unreadMsgCount);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        onConversationSelect.onSelectConversation(getAdapterPosition(),items.get(getAdapterPosition()));
        }
    }

    public interface OnConversationSelect {

        public void onSelectConversation(int position,ConversationsItem conversationsItem);
    }
}
