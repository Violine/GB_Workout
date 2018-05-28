package com.vdovenkov.alexander.workout;

public class Exercise {

    private String exerciseName;
    private String exerciseDescription;
    private long exerciseRecordDate;
    private int exerciseRecord;

    public Exercise(String exerciseName, String exerciseDescription, long exerciseRecordDate, int exerciseRecord) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseRecordDate = exerciseRecordDate;
        this.exerciseRecord = exerciseRecord;
    }

    Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public Exercise(String exerciseName, String exerciseDescription) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public long getExerciseRecordDate() {
        return exerciseRecordDate;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public int getExerciseRecord() {
        return exerciseRecord;
    }

    public void setExerciseRecordDate(int exerciseDate) {
        this.exerciseRecordDate = exerciseDate;
    }
}
