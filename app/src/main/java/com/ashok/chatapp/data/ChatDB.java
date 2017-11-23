package com.ashok.chatapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ashok on 22/11/17.
 */

@Database(entities = {Message.class}, version = 1)
public abstract class ChatDB extends RoomDatabase {
    private static final Object sLock = new Object();
    private static ChatDB INSTANCE;

    public static ChatDB getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ChatDB.class, "Chat.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract MessageDao messageDao();
}