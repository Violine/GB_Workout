package com.vdovenkov.alexander.workout;

import java.util.ArrayList;
import java.util.List;


public class ExerciseList {

    protected static List<Exercise> exercises;

    static {
        generateTestExercise();
    }

    ExerciseList() {

    }

    protected static void generateTestExercise() {
        exercises = new ArrayList<>();
        exercises.add(new Exercise("Подтягивание"));
        exercises.add(new Exercise("Отжимания"));
        exercises.add(new Exercise("Приседания"));
    }

    protected static void removeExerciseFromList(int position) {
        exercises.remove(position);
    }

    protected static void addExerciseToList(Exercise exercise) {
        exercises.add(exercise);
    }

    protected static List<Exercise> getExercisesList() {
        return exercises;
    }
    protected static Exercise getExerciseFromList(int position){
        return exercises.get(position);
    }

}
