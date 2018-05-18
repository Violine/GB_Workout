package com.vdovenkov.alexander.workout;

public class Exercise {

    private String exerciseName;
    private String exerciseDescription;
    private int exerciseDate;

    Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
    }



    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getExerciseDate() {
        return exerciseDate;
    }



    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public void setExerciseDate(int exerciseDate) {
        this.exerciseDate = exerciseDate;
    }
}
