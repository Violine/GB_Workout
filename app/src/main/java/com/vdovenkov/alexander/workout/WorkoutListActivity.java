package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class WorkoutListActivity extends AppCompatActivity {

    private List<Exercise> exercises;

    private RecyclerView recView;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUI();

    }

    private void initUI() {
        setContentView(R.layout.activity_workout_list);
        exercises = ExerciseList.getExercisesList();
        recView = findViewById(R.id.recview);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkoutListActivity.this, "Нажата кнопка добавления упражнения", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(linearLayoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(exercises);
        recView.setAdapter(adapter);
    }

}