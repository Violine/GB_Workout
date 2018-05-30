package com.vdovenkov.alexander.workout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ExerciseList {

    static WorkoutDB sqlHelper;
    static SQLiteDatabase workoutDB;
    static Context context;
    static int WorkourDbId;

    public static List<Exercise> exercises;

    public ExerciseList(Context context) {
        this.context = context;
    }

    public static List<Exercise> readExerciseFromDB() {
        initWorkoutDB(context);
        exercises = new ArrayList<>();
        Cursor cursor = workoutDB.query(WorkoutDB.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idColumnIndex = cursor.getColumnIndex(WorkoutDB._ID);
            int workoutNameColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_NAME);
            int workoutDescriptionColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_DESCRIPTION);
            int workoutRecordColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_RECORD);
            int workoutRecordDateColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_RECORD_DATE);
            do {
                Exercise exercise = new Exercise(cursor.getString(workoutNameColumnIndex), cursor.getString(workoutDescriptionColumnIndex),
                        cursor.getString(workoutRecordDateColumnIndex), cursor.getString(workoutRecordColumnIndex));
                exercise.setExerciseID(cursor.getInt(idColumnIndex));
                exercises.add(exercise);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeWorkoutDB();
        return exercises;
    }

    static void writeExerciseToDB(Context context, Exercise exercise) {
        initWorkoutDB(context);
        ContentValues workoutContent = new ContentValues();
        workoutContent.put(WorkoutDB.WORKOUT_NAME, exercise.getExerciseName());
        workoutContent.put(WorkoutDB.WORKOUT_DESCRIPTION, exercise.getExerciseDescription());
        workoutContent.put(WorkoutDB.WORKOUT_RECORD_DATE, exercise.getExerciseRecordDate());
        workoutContent.put(WorkoutDB.WORKOUT_RECORD, exercise.getExerciseRecord());
        workoutDB.insert(WorkoutDB.TABLE_NAME, null, workoutContent);
        readExerciseFromDB();
        closeWorkoutDB();
    }

    private static void initWorkoutDB(Context context) {
        sqlHelper = new WorkoutDB(context);
        workoutDB = sqlHelper.getWritableDatabase();
    }

    private static void closeWorkoutDB() {
        workoutDB.close();
        sqlHelper.close();
    }

    protected static void removeExerciseFromList(int position) {
        initWorkoutDB(context);
        int removedID = exercises.get(position).getExerciseID();
        workoutDB.delete(WorkoutDB.TABLE_NAME, WorkoutDB._ID + " = " + removedID, null);
        closeWorkoutDB();
    }

    protected static void addExerciseToList(Exercise exercise) {
        exercises.add(exercise);
    }

    protected static List<Exercise> getExercisesList() {
        return exercises;
    }

    protected static Exercise getExerciseFromList(int id) {
        initWorkoutDB(context);
        Exercise exercise = null;
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor cursor = workoutDB.query(WorkoutDB.TABLE_NAME, null, WorkoutDB._ID + " = ?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int workoutNameColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_NAME);
            int workoutDescriptionColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_DESCRIPTION);
            int workoutRecordColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_RECORD);
            int workoutRecordDateColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_RECORD_DATE);
            do {
                exercise = new Exercise(cursor.getString(workoutNameColumnIndex), cursor.getString(workoutDescriptionColumnIndex),
                        cursor.getString(workoutRecordDateColumnIndex), cursor.getString(workoutRecordColumnIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeWorkoutDB();
        return exercise;
    }

    public static void saveDataToDB(int id, String recordValueText, String currentDate) {
        initWorkoutDB(context);
        String[] selectionArgs = new String[]{String.valueOf(id)};
        ContentValues content = new ContentValues();
        content.put(WorkoutDB.WORKOUT_RECORD_DATE, currentDate);
        content.put(WorkoutDB.WORKOUT_RECORD, recordValueText);
        workoutDB.update(WorkoutDB.TABLE_NAME, content, WorkoutDB._ID + " = ?", selectionArgs);
        readExerciseFromDB();
        closeWorkoutDB();
    }
}
