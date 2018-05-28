package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class WorkoutDB extends SQLiteOpenHelper implements BaseColumns {
    Context context;
    private static final String DATABASE_NAME = "workout_database.db";
    private static final int VERSION = 1;
    protected static final String WORKOUT_NAME = "workoutname";
    protected static final String TABLE_NAME = "workout_table";
    protected static final String WORKOUT_RECORD = "workout_record";
    protected static final String WORKOUT_RECORD_DATE = "workout_record_date";
    protected static final String WORKOUT_DESCRIPTION = "workout_description";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + TABLE_NAME + " (" + WorkoutDB._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + WORKOUT_NAME + " VARCHAR(255), " + WORKOUT_DESCRIPTION + " TEXT, "
            + WORKOUT_RECORD + " VARCHAR(255), "+ WORKOUT_RECORD_DATE + " VARCHAR(255));";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("LOF_TAG", context.getString(R.string.database_update_message) + newVersion);
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public WorkoutDB(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
}