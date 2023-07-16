package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.retro.logger.adapter.LogAdapter;
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.LogInterface;

public class LoggerActivity extends AppCompatActivity {

    LogAdapter logAdapter;
    RecyclerView rvLog;
    SessionDB sessionDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        rvLog= findViewById(R.id.rvLog);
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
    }
}