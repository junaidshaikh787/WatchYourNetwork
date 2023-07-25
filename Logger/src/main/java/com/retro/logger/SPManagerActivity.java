package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.retro.logger.adapter.SpAdapter;
import com.retro.logger.model.SpModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SPManagerActivity extends AppCompatActivity {

    List<SpModel> data;
    SpAdapter spAdapter;
    RecyclerView rvSp;
    EditText search;
    ImageView delete;
    String charSequenceFilter;
    String profile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spmanager);

        rvSp = findViewById(R.id.rvSp);
        search = findViewById(R.id.editSearchSp);
        delete = findViewById(R.id.log_delete);

        delete.setVisibility(View.INVISIBLE);

        data = new ArrayList<>();

        profile = getIntent().getStringExtra("fileName");

        if(profile != null && !profile.equals("")){
            getShare();
        }

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequenceFilter = charSequence.toString().toLowerCase();
                List<SpModel> list = new ArrayList<>();
                for (SpModel item : data) {
                    if(item.getKey().toLowerCase().contains(charSequenceFilter) || item.getValue().toLowerCase().contains(charSequenceFilter)) {
                        list.add(item);
                    }
                }
                spAdapter.setData(list);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getShare() {
        if(profile.contains("xml")){
            profile = profile.substring(0, profile.length()-4);
        }

        SharedPreferences sp2 = getSharedPreferences(profile, MODE_PRIVATE);
        Map<String, ?> map = sp2.getAll();

        for (Map.Entry<String, ?> entry : map.entrySet()) {
            data.add(new SpModel(entry.getKey(), entry.getValue().toString()));
        }

        spAdapter = new SpAdapter(data);
        rvSp.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvSp.setAdapter(spAdapter);
        Log.d("All Share", profile);
        Log.d("All Share", new Gson().toJson(map));
    }
}