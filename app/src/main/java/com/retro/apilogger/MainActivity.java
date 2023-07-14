package com.retro.apilogger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.retro.logger.LoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}