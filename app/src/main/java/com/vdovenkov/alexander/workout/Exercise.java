package com.vdovenkov.alexander.workout;

public class Exercise {

    private String exerciseName;
    private String exerciseDescription;
    private int exerciseRecordDate;

    Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }
// TODO Сделать перегруженный конструктор, чтобы пользователь мог вводить не только название упраженения, но и описания.
    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getExerciseRecordDate() {
        return exerciseRecordDate;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseRecordDate(int exerciseDate) {
        this.exerciseRecordDate = exerciseDate;
    }
}
