package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.retro.logger.adapter.PrefAdapter;

import java.io.File;

public class LogSharedPrefActivity extends AppCompatActivity {
    PrefAdapter prefAdapter;
    RecyclerView rvPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_shared_pref);
        rvPref = findViewById(R.id.rvPref);
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