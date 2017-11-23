package com.ashok.chatapp.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;

import com.ashok.chatapp.util.Util;

/**
 * Created by ashok on 22/11/17.
 */
@Entity(tableName = "messages")
public final class Message {
    public static DiffCallback<Message> diffCallback = new DiffCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }
    };

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String body;
    public String senderId;
    public String receiverId;
    public long timestamp;

    public Message(String body, String senderId, String receiverId) {
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.timestamp = Util.getTimeStamp();
    }
}