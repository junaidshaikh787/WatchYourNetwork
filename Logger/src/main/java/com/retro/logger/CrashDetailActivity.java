package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.retro.logger.model.ExceptionModel;
import com.retro.logger.model.LogModel;

public class CrashDetailActivity extends AppCompatActivity {

    TextView tvExceptionType, timing, tvException;

    ExceptionModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_detail);

        tvException = findViewById(R.id.tvException);
        tvExceptionType = findViewById(R.id.tvExceptionType);
        timing = findViewById(R.id.tvTiming);

        data = (ExceptionModel) getIntent().getExtras().get("crash");

        String exceptionType = data.getEXCEPTION_TYPE();
        if(exceptionType.equalsIgnoreCase("Ex")){
            exceptionType = data.getSTACKTRACE().split(":")[0];
        }
        tvExceptionType.setText(exceptionType);
        tvException.setText(data.getSTACKTRACE());
        timing.setText(data.getEXCEPTION_TIME());
    }
}