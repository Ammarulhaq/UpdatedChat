package com.example.conversationlisting.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conversationlisting.R;
import com.example.conversationlisting.modelclasses.chatMessages.Data;
import com.example.conversationlisting.utilities.DateFormatConverter;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public void onBindViewHolder(@NonNull final VHolder holder, final int position) {


        if (items.getMessages().get(position).getResourceId() != 386) {
            if(items.getMessages().get(position).getIsDeleted())
            {
                holder.sendchatMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_delete_24,0,0,0);
                holder.sendchatMsg.setText("Message Removed");

            }
            else
            {
                if(items.getMessages().get(position).getMessage()==null)
                {
                  //  String returnedPath = Utils.getRealPathFromURI_API19(context, uri);

                    if(items.getMessages().get(position).getFilename() ==null)
                    {
                        holder.sendchatMsg.setText("null");
                    }
                    else
                    {
                        holder.sendchatMsg.setText(items.getMessages().get(position).getFilename().toString());
                    }

                }
                else
                    {

                        holder.sendchatMsg.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                        holder.sendchatMsg.setText(items.getMessages().get(position).getMessage());

                    }
            }
            RelativeLayout.LayoutParams chatprms=(RelativeLayout.LayoutParams)holder.sendchatMsg.getLayoutParams();
            chatprms.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            RelativeLayout.LayoutParams prms=(RelativeLayout.LayoutParams)holder.sendTime.getLayoutParams();
            prms.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.sendchatMsg.setBackground(ContextCompat.getDrawable(context, R.drawable.sendchatboxborder));
        }
        else
          {


              if(items.getMessages().get(position).getIsDeleted())
              {
                  holder.sendchatMsg.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_delete_24,0,0,0);
                  holder.sendchatMsg.setText("Message Removed");

              }
              else
              {
                  if(items.getMessages().get(position).getMessage()==null)
                  {

                      if(items.getMessages().get(position).getFilename() ==null)
                      {
                          holder.sendchatMsg.setText("null");
                      }
                      else
                      {
                          holder.sendchatMsg.setText(items.getMessages().get(position).getFilename().toString());
                      }

                  }
                  else
                  {
                      holder.sendchatMsg.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                      holder.sendchatMsg.setText(items.getMessages().get(position).getMessage());

                  }

              }
              RelativeLayout.LayoutParams chatprms=(RelativeLayout.LayoutParams)holder.sendchatMsg.getLayoutParams();
              chatprms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
              RelativeLayout.LayoutParams prms=(RelativeLayout.LayoutParams)holder.sendTime.getLayoutParams();
              prms.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
              holder.sendchatMsg.setBackground(ContextCompat.getDrawable(context, R.drawable.recievechatboxborder));
        }

        if (position < getItemCount() - 1)
        {
            // String d=DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("dd-MM-YYYY"));

            long absolutedays = DateFormatConverter.Companion.GetDays(new SimpleDateFormat("dd-MM-YYYY",Locale.getDefault()).format(new Date()),DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("dd-MM-YYYY")),true);

            long relativedays=DateFormatConverter.Companion.GetDays(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("dd-MM-YYYY")),DateFormatConverter.Companion.GetDate(items.getMessages().get(position+1).getSentDatetime(), new SimpleDateFormat("dd-MM-YYYY")),true);
            long minutes=DateFormatConverter.Companion.GetDays(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("hh:mm a")), DateFormatConverter.Companion.GetDate(items.getMessages().get(position + 1).getSentDatetime(), new SimpleDateFormat("hh:mm a")),false);
            if (items.getMessages().get(position).getResourceId().equals(items.getMessages().get(position + 1).getResourceId()) && (absolutedays==0) && (relativedays==0) && (minutes==0))
            {

                holder.sendTime.setVisibility(View.GONE);
                holder.usrImg.setVisibility(View.GONE);
                holder.senderName.setVisibility(View.GONE);
            }
            else
            {
                // Sender Reciever Boundary Check
               if(!(items.getMessages().get(position).getResourceId().equals(items.getMessages().get(position + 1).getResourceId())) && (!(items.getMessages().get(position).getResourceId().equals(386))))
               {
                       holder.usrImg.setVisibility(View.VISIBLE);
                       holder.senderName.setVisibility(View.VISIBLE);
                       Picasso.with(context).load(items.getMessages().get(position).getResourceImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.usrImg, new com.squareup.picasso.Callback() {
                           @Override
                           public void onSuccess() {
                               holder.senderName.setText(items.getMessages().get(position).getDisplayName());
                               Log.d("Success","Image Loaded Successfully");
                           }

                           @Override
                           public void onError() {
                               Log.d("Error","Problem Occured while Loading Image");

                           }
                       });
               }
               else
               {
                   holder.usrImg.setVisibility(View.GONE);
                   holder.senderName.setVisibility(View.GONE);
               }
                   if(absolutedays>=1 || relativedays>=1)
                   {

                       holder.sendTime.setVisibility(View.VISIBLE);
                       holder.sendTime.setText(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("EEEE,hh:mm a")));

                   }
                   else
                       {
                           if(minutes >=1)
                           {
                               holder.sendTime.setVisibility(View.VISIBLE);
                               holder.sendTime.setText(DateFormatConverter.Companion.GetDate(items.getMessages().get(position).getSentDatetime(), new SimpleDateFormat("hh:mm a")));

                           }
                           else
                           {
                               holder.sendTime.setVisibility(View.GONE);
                           }

                   }


            }

//
//                else
//                {
//                    holder.groupDate.setVisibility(View.GONE);
//                }


        }

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
//













    @Override
    public int getItemCount() {

        return(items!=null ? items.getMessages().size(): 0) ;

    }

    public class VHolder extends RecyclerView.ViewHolder{

        TextView sendchatMsg;
        TextView rcvchatMsg;
        TextView rcvTime;
        TextView sendTime;
        TextView groupDate;
        SeekBar mediaplayer;
        TextView senderName;
        ImageView usrImg;
        public VHolder(@NonNull View itemView) {
            super(itemView);

            sendchatMsg=itemView.findViewById(R.id.sendchat);
            //rcvchatMsg=itemView.findViewById(R.id.rcvchat);
            sendTime=itemView.findViewById(R.id.sndTime);
          //  groupDate=itemView.findViewById(R.id.dategroup);
            //mediaplayer=itemView.findViewById(R.id.mediaplayer);
            usrImg=itemView.findViewById(R.id.usrImg);
            senderName=itemView.findViewById(R.id.usrname);
           // rcvTime=itemView.findViewById(R.id.rcvTime);
        }
    }
}
