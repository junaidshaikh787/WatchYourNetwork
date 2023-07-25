package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.retro.logger.adapter.PrefAdapter;

import java.io.File;

public class LogSharedPrefActivity extends AppCompatActivity {
    PrefAdapter prefAdapter;
    RecyclerView rvPref;
    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_shared_pref);
        rvPref = findViewById(R.id.rvPref);
        delete = findViewById(R.id.log_delete);
        delete.setVisibility(View.GONE);
        FetchFiles();

    }

    public void FetchFiles() {
        File prefsdir = new File(getApplicationInfo().dataDir, "shared_prefs");

        if (prefsdir.exists() && prefsdir.isDirectory()) {
            String[] list = prefsdir.list();
            prefAdapter = new PrefAdapter(list);
        }

        rvPref.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvPref.setAdapter(prefAdapter);
    }
}