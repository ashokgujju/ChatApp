package com.ashok.chatapp.data;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by ashok on 22/11/17.
 */

@Dao
public interface MessageDao {
    @Query("SELECT * FROM messages ORDER BY id DESC")
    LivePagedListProvider<Integer, Message> loadMessages();

    @Insert
    void insertMessage(Message message);

    @Delete
    void deleteMessage(Message message);
}