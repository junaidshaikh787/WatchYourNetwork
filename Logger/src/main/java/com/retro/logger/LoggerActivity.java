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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.retro.logger.adapter.LogAdapter;
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.LogInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    ImageView delete, ivSp;
    List<LogModel> data;
    LinearLayout llCrashes;
    LinearLayout llSharedPref;
    TextView tvCrash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);

        rvLog= findViewById(R.id.rvLog);
        search= findViewById(R.id.editSearch);
        delete= findViewById(R.id.delete);
        llCrashes= findViewById(R.id.llCrashes);
        llSharedPref= findViewById(R.id.llSharedPref);
        tvCrash= findViewById(R.id.tvCrash);
        ivSp = findViewById(R.id.sp);

        ivSp.setVisibility(View.VISIBLE);

        sessionDB=new SessionDB(this);
        sessionDB.getDB();
        data = sessionDB.getLog();
        Collections.reverse(data);
        logAdapter=new LogAdapter(data, new LogInterface() {
            @Override
            public void onLogClick(LogModel log) {
                Intent in=new Intent(LoggerActivity.this,LogDetailsActivity.class);
                in.putExtra("log",log);
                startActivity(in);
            }
        });

        rvLog.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvLog.setAdapter(logAdapter);

        delete.setOnClickListener(v-> {
            int delete = sessionDB.deleteRecord();
            if(delete > 1){
                data.clear();
                data = new ArrayList<>();
                logAdapter.setData(data);
            }

        });

        ivSp.setOnClickListener(v-> {
            Intent in=new Intent(LoggerActivity.this, SPManagerActivity.class);
            in.putExtra("fileName", LogAppLib.context.getPackageName());
            startActivity(in);
        });

        crash.setOnClickListener(v-> {
            Intent in=new Intent(LoggerActivity.this, CrashListActivity.class);
            startActivity(in);
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                isButtonClicked = false;

            }
        };

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequenceFilter = charSequence.toString().toLowerCase();
                List<LogModel> data = sessionDB.getLog();
                List<LogModel> list = new ArrayList<>();
                for (LogModel item : data) {
                    if(item.getURL().toLowerCase().contains(charSequenceFilter) || item.getSTATUS().toLowerCase().contains(charSequenceFilter)) {
                        list.add(item);
                    }
                }
                Collections.reverse(list);
                logAdapter.setData(list);
                /*if (isButtonClicked) {
                    handler.removeCallbacks(runnable);
                    isButtonClicked = false;
                } else {
                    isButtonClicked = true;
                    handler.postDelayed(runnable, 500);
                }*/
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCrash.setText(sessionDB.getException().size()+"");
    }
}