package com.retro.logger;


import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import timber.log.Timber;

public class LoggingInterceptor implements Interceptor {

    Context context;
    SessionDB sessionDB;
    public LoggingInterceptor(Context context) {
        this.context=context;
        sessionDB=new SessionDB(context);
        sessionDB.getDB();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        Request request = chain.request();

        long t1 = System.nanoTime();
        //Timber.i("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());
        //Timber.v("REQUEST BODY BEGIN\n%s\nREQUEST BODY END", bodyToString(request));

        Response response = chain.proceed(request);

        //ResponseBody responseBody =  response.peekBody(100000);
        String responseBodyString = response.peekBody(1000000).string();

        // now we have extracted the response body but in the process
        // we have consumed the original reponse and can't read it again
        // so we need to build a new one to return from this method

        //Response newResponse = response.newBuilder().body(ResponseBody.create(responseBody.contentType(), responseBodyString.getBytes())).build();

        long t2 = System.nanoTime();
        //Timber.i("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers());
        //Timber.v("RESPONSE BODY BEGIN:\n%s\nRESPONSE BODY END", responseBodyString);

        sessionDB.InsertSessionData(request.method(),String.valueOf(response.code()),request.url().toString(),request.headers().toString(),
                bodyToString(request),response.headers().toString(),responseBodyString, String.valueOf(((t2 - t1) / 1e6d)));

        return response;
    }

    private static String bodyToString(final Request request){

        if(request != null) {
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            } catch (Exception ex) {
                ex.printStackTrace();
                return "did not work";
            }
        }
        return "";
    }
}
