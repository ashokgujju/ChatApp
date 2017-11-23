package com.ashok.chatapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.ashok.chatapp.R;
import com.ashok.chatapp.data.Message;
import com.ashok.chatapp.util.AppConstants;
import com.ashok.chatapp.viewmodel.ConversationViewModel;

public class ConversationActivity extends AppCompatActivity {

    private ConversationViewModel viewModel;
    private RecyclerView mRecyclerView;
    private EditText mMessageBody;

    private MessageAdapter adapter;

    private RecyclerView.AdapterDataObserver adapterDataObserver =
            new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    super.onItemRangeInserted(positionStart, itemCount);
                    mRecyclerView.scrollToPosition(0);
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        viewModel = ViewModelProviders.of(this).get(ConversationViewModel.class);

        mMessageBody = findViewById(R.id.message);
        mRecyclerView = findViewById(R.id.list);

        adapter = new MessageAdapter();
        mRecyclerView.setAdapter(adapter);

        viewModel.messagesList
                .observe(this, messages -> adapter.setList(messages));
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.registerAdapterDataObserver(adapterDataObserver);
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.unregisterAdapterDataObserver(adapterDataObserver);
    }

    public void sendMessage(View view) {
        String body = mMessageBody.getText().toString().trim();
        if (!body.isEmpty()) {
            Message message = new Message(body, AppConstants.ME, AppConstants.OTHER);
            viewModel.sendNewMessage(message);
            mMessageBody.setText(null);
        }
    }
}