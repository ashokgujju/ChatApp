package com.ashok.chatapp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ashok.chatapp.R;
import com.ashok.chatapp.data.Message;
import com.ashok.chatapp.util.AppConstants;
import com.ashok.chatapp.util.Util;

/**
 * Created by ashok on 22/11/17.
 */

public class MessageAdapter extends android.arch.paging.PagedListAdapter<Message,
        MessageAdapter.MessageViewHolder> {

    protected MessageAdapter() {
        super(Message.diffCallback);
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        Message message = getItem(position);
        if (message != null) {
            holder.bindTo(message);
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView mContent, mTime;

        public MessageViewHolder(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.content);
            mTime = itemView.findViewById(R.id.time);
        }

        public void bindTo(Message message) {
            if (message.senderId.equals(AppConstants.ME)) {
                itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            } else {
                itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
            mContent.setText(message.body);
            mTime.setText(Util.getTime(message.timestamp));
        }
    }
}