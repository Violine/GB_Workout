package com.vdovenkov.alexander.workout;

import java.util.ArrayList;
import java.util.List;


public class ExerciseList {

    private static List<Exercise> exercises;

    static {
        addExercise();
    }

    ExerciseList() {

    }

    private static void addExercise() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise("Подтягивание"));
        exercises.add(new Exercise("Отжимания"));
        exercises.add(new Exercise("Приседания"));
    }
    public static void removeExercise(){

    }

    public static List<Exercise> getExercisesList() {
        return exercises;
    }
}
