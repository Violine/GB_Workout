package com.vdovenkov.alexander.workout;

import java.util.ArrayList;
import java.util.List;


public class ExerciseList {

    private static List<Exercise> exercises;

    static {
        generateTestExercise();
    }

    ExerciseList() {

    }

    private static void generateTestExercise() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise("Подтягивание"));
        exercises.add(new Exercise("Отжимания"));
        exercises.add(new Exercise("Приседания"));
    }

    public static void removeExercise() {
    }

    protected static void addExerciseToList(Exercise exercise) {
        exercises.add(exercise);
    }

    public static List<Exercise> getExercisesList() {
        return exercises;
    }
}
