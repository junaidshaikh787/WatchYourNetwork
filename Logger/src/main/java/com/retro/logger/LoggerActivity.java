package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.retro.logger.adapter.LogAdapter;
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.LogInterface;

import java.util.ArrayList;
import java.util.List;

public class LoggerActivity extends AppCompatActivity {

    LogAdapter logAdapter;
    RecyclerView rvLog;
    SessionDB sessionDB;
    EditText search;
    private boolean isButtonClicked = false;
    private Handler handler;
    private Runnable runnable;
    CharSequence charSequenceFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        rvLog= findViewById(R.id.rvLog);
        search= findViewById(R.id.editSearch);
        sessionDB=new SessionDB(this);
        sessionDB.getDB();
        logAdapter=new LogAdapter(sessionDB.getLog(), new LogInterface() {
            @Override
            public void onLogClick(LogModel log) {
                Intent in=new Intent(LoggerActivity.this,LogDetailsActivity.class);
                in.putExtra("log",log);
                startActivity(in);
            }
        });

        rvLog.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvLog.setAdapter(logAdapter);

        runnable = new Runnable() {
            @Override
            public void run() {
                isButtonClicked = false;
                List<LogModel> data = sessionDB.getLog();
                List<LogModel> list = new ArrayList<>();
                for (LogModel item : data) {
                    if(item.getURL().contains(charSequenceFilter)) {
                        list.add(item);
                    }
                }
                logAdapter.setData(list);
            }
        };

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequenceFilter = charSequence;
                if (isButtonClicked) {
                    handler.removeCallbacks(runnable);
                    isButtonClicked = false;
                } else {
                    isButtonClicked = true;
                    handler.postDelayed(runnable, 500);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}