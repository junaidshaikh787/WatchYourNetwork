package com.retro.logger;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SessionDB extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Session.db";
    private static SessionDB sInstance;


    //region DATABASE STRING CONSTANT
    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String INT_TYPE = " INTEGER";
    private static final String UNIQUE = " UNIQUE";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    private static final String AUTOINCREMENT = " AUTOINCREMENT";
    private static final String COMMA_SEP = ",";
    private static final String NOT_NULL = " NOT NULL";
    private static final String DEFAULT = " DEFAULT";
    private static final String SPACE = "";
    private static final String ZERO = " 0";
    //endregion

    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_CALLMETHOD = "CallMethod";
    public static final String COLUMN_STAUS = "Staus";
    public static final String COLUMN_URL = "Url";
    public static final String COLUMN_REQUEST_HEADER = "RequestHeader";
    public static final String COLUMN_REQUEST = "Request";
    public static final String COLUMN_RESPONSE_HEADER = "ResponseHeader";
    public static final String COLUMN_RESPONSE = "Response";
    public static final String COLUMN_RESPONSE_TIME = "ResponseTime";
    public static final String SESSION_TABLE = "Session";

    public static final String CREATE_SESSION_TABLE = "CREATE TABLE IF NOT EXISTS " + SESSION_TABLE + "(" +

            COLUMN_ID + INT_TYPE + PRIMARY_KEY + AUTOINCREMENT + COMMA_SEP +
            COLUMN_CALLMETHOD + TEXT_TYPE + "," +
            COLUMN_STAUS + TEXT_TYPE + "," +
            COLUMN_URL + TEXT_TYPE + "," +
            COLUMN_REQUEST_HEADER + TEXT_TYPE + "," +
            COLUMN_REQUEST + TEXT_TYPE + "," +
            COLUMN_RESPONSE_HEADER + TEXT_TYPE + "," +
            COLUMN_RESPONSE + TEXT_TYPE + "," +
            COLUMN_RESPONSE_TIME + TEXT_TYPE + "," +

            "UNIQUE  (" + COLUMN_ID + ") ON CONFLICT REPLACE " + ");";

    private static final String DELETE_SESSION_TABLE = "DROP TABLE IF EXISTS " + SESSION_TABLE;

    public SessionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized SessionDB getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SessionDB(context.getApplicationContext());
        }
        return sInstance;
    }

    public SQLiteDatabase getDB() {
        return this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SESSION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_SESSION_TABLE);
        onCreate(sqLiteDatabase);
    }

    public int InsertSessionData(String CALLMETHOD,String STAUS,String URL,String REQUEST_HEADER,
                                 String REQUEST,String RESPONSE_HEADER,String RESPONSE,String RESPONSE_TIME) {
        try {
            SQLiteDatabase sql = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(COLUMN_CALLMETHOD, CALLMETHOD);
            values.put(COLUMN_STAUS, STAUS);
            values.put(COLUMN_URL, URL);
            values.put(COLUMN_REQUEST_HEADER, REQUEST_HEADER);
            values.put(COLUMN_REQUEST, REQUEST);
            values.put(COLUMN_RESPONSE_HEADER, RESPONSE_HEADER);
            values.put(COLUMN_RESPONSE, RESPONSE);
            values.put(COLUMN_RESPONSE_TIME, RESPONSE_TIME);

            try {
                long newRowId = sql.insert(SESSION_TABLE, null, values);
                if (newRowId > 0) {
                    return 1;
                }
                //sql.close();
            } catch (Exception ex) {
                Log.e("Exception ", ex.toString());
            }
            //sql.close();
        } catch (Exception ex) {
            Log.e("Exception ", ex.toString());
        }
        return 0;
    }
}
