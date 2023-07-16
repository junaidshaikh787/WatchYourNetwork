package com.retro.logger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.retro.logger.model.LogModel;

public class LogDetailsActivity extends AppCompatActivity {

    TextView tvMethod;
    TextView tvTiming;
    TextView tvUrl;
    TextView tvStatus;
    TextView tvRequestHeader;
    TextView tvRequest;
    TextView tvResponseHeader;
    TextView tvResponse;
    LogModel log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);

        log= (LogModel) getIntent().getExtras().get("log");


        tvMethod = findViewById(R.id.tvMethod);
        tvTiming = findViewById(R.id.tvTiming);
        tvUrl = findViewById(R.id.tvUrl);
        tvStatus = findViewById(R.id.tvStatus);
        tvRequestHeader = findViewById(R.id.tvRequestHeader);
        tvRequest = findViewById(R.id.tvRequest);
        tvResponseHeader = findViewById(R.id.tvResponseHeader);
        tvResponse = findViewById(R.id.tvResponse);

        tvMethod.setText(log.getCALLMETHOD());
        tvTiming.setText(log.getAPI_CALL_TIME());
        tvUrl.setText(log.getURL());
        tvStatus.setText(log.getSTATUS());
        tvRequestHeader.setText(log.getREQUEST_HEADER());
        tvRequest.setText(log.getREQUEST());
        tvResponseHeader.setText(log.getRESPONSE_HEADER());
        tvResponse.setText(log.getRESPONSE());

    }
}