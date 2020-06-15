package com.example.conversationlisting.adapters;

import android.content.Context;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conversationlisting.R;
import com.example.conversationlisting.modelclasses.chatMessages.Data;
import com.example.conversationlisting.utilities.DateFormatConverter;

public class chatListingAdapter extends RecyclerView.Adapter<chatListingAdapter.VHolder> {
    private Context context;
    private Data items;

    public chatListingAdapter(Context context, Data items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chatmessagetile, parent, false);
        return new VHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {

        if(items.getMessages().get(position).getResourceId()!=132)
        {
            holder.sendchatMsg.setVisibility(View.GONE);
            holder.rcvchatMsg.setText(items.getMessages().get(position).getMessage());
            holder.rcvTime.setText(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime()));
        }
        else
        {
            holder.rcvchatMsg.setVisibility(View.GONE);
            holder.sendchatMsg.setText(items.getMessages().get(position).getMessage());
            holder.sendTime.setText(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime()));
        }



//        Picasso.with(context).load(items.get(position).getLatestMessage().getResourceImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ownerImage, new com.squareup.picasso.Callback() {
//            @Override
//            public void onSuccess() {
//                Log.d("Success","Image Loaded Successfully");
//            }
//
//            @Override
//            public void onError() {
//                Log.d("Error","Problem Occured while Loading Image");
//
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return(items!=null ? items.getMessages().size(): 0) ;

    }

    public class VHolder extends RecyclerView.ViewHolder{

        TextView sendchatMsg;
        TextView rcvchatMsg;
        TextView rcvTime;
        TextView sendTime;
        public VHolder(@NonNull View itemView) {
            super(itemView);

            sendchatMsg=itemView.findViewById(R.id.sendchat);
            rcvchatMsg=itemView.findViewById(R.id.rcvchat);
            sendTime=itemView.findViewById(R.id.sndTime);
            rcvTime=itemView.findViewById(R.id.rcvTime);
        }
    }
}
