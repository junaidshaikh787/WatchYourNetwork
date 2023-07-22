package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.retro.logger.adapter.CrashAdapter;
import com.retro.logger.adapter.LogAdapter;
import com.retro.logger.model.ExceptionModel;
import com.retro.logger.model.LogModel;
import com.retro.logger.utils.CrashInterface;
import com.retro.logger.utils.LogInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrashListActivity extends AppCompatActivity {

    CrashAdapter crashAdapter;
    RecyclerView rvCrash;
    SessionDB sessionDB;
    EditText crashSearch;
    ImageView delete;
    List<ExceptionModel> data;
    RelativeLayout crash;
    String charSequenceFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_list);

        rvCrash = findViewById(R.id.rvCrash);
        crashSearch = findViewById(R.id.editSearchCrash);
        crash = findViewById(R.id.crash);
        delete = findViewById(R.id.delete);

        crash.setVisibility(View.INVISIBLE);


        sessionDB=new SessionDB(this);
        sessionDB.getDB();
        data = sessionDB.getException();

        Collections.reverse(data);
        crashAdapter =new CrashAdapter(data, new CrashInterface() {
            @Override
            public void onLogClick(ExceptionModel crash) {
                Intent in=new Intent(CrashListActivity.this, CrashDetailActivity.class);
                in.putExtra("crash", crash);
                startActivity(in);
            }
        });

        rvCrash.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvCrash.setAdapter(crashAdapter);

        delete.setOnClickListener(v-> {
            if(data.size() > 0){
                int delete = sessionDB.deleteExceptionRecord();
                if(delete > 1){
                    data.clear();
                    data = new ArrayList<>();
                    crashAdapter.setData(data);
                }
            }
        });

        crashSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequenceFilter = charSequence.toString().toLowerCase();
                List<ExceptionModel> data = sessionDB.getException();
                List<ExceptionModel> list = new ArrayList<>();
                for (ExceptionModel item : data) {
                    if(item.getSTACKTRACE().toLowerCase().contains(charSequenceFilter) || item.getEXCEPTION_TIME().toLowerCase().contains(charSequenceFilter)) {
                        list.add(item);
                    }
                }
                Collections.reverse(list);
                crashAdapter.setData(list);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}