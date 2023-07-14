package com.retro.logger;

import android.content.Context;

public class LogAppLib  {

    Context context;

    public LogAppLib(Context context) {
        this.context = context;
    }

    public LoggingInterceptor getInterceptor(){
        return new LoggingInterceptor(context);
    }
}
