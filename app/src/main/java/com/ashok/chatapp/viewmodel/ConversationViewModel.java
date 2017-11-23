package com.ashok.chatapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.ashok.chatapp.data.ChatDB;
import com.ashok.chatapp.data.Message;
import com.ashok.chatapp.data.MessageDao;
import com.ashok.chatapp.util.DummyMessages;

import java.util.concurrent.Executors;

/**
 * Created by ashok on 22/11/17.
 */

public class ConversationViewModel extends AndroidViewModel {

    public LiveData<PagedList<Message>> messagesList;
    private MessageDao messageDao;

    public ConversationViewModel(@NonNull Application application) {
        super(application);
        messageDao = ChatDB.getInstance(application).messageDao();
        loadMessages();
    }

    public void loadMessages() {
        messagesList = messageDao.loadMessages().create(0,
                new PagedList.Config.Builder()
                        .setPageSize(30)
                        .setEnablePlaceholders(true)
                        .build());
    }

    public void sendNewMessage(Message message) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                messageDao.insertMessage(message);
                addDelay();
                messageDao.insertMessage(DummyMessages.getRandomMessage());
            }

            private void addDelay() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}