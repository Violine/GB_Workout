package com.vdovenkov.alexander.workout;

public class Exercise {

    private String exerciseName;
    private String exerciseDescription;
    private String exerciseRecordDate;
    private int exerciseRecord;
    private int exerciseID;


    public Exercise(String exerciseName, String exerciseDescription, String  exerciseRecordDate, int exerciseRecord) {
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

    public String getExerciseRecordDate() {
        return exerciseRecordDate;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public int getExerciseID() {
        return exerciseID;
    }

    public int getExerciseRecord() {
        return exerciseRecord;
    }

    public void setExerciseID(int exerciseID) {
        this.exerciseID = exerciseID;
    }

}
