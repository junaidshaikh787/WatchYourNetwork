package com.retro.logger;

import static com.retro.logger.FabButton.initFab;
import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LogAppLib {

    public static Context context;

    public LogAppLib(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(new CustomizedExceptionHandler(context));
    }

    public LoggingInterceptor getInterceptor() {
        return new LoggingInterceptor(context);
    }


    public void initFabs(AppCompatActivity activity) {
        initFab(activity);

    }
}
