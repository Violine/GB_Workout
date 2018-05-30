package com.vdovenkov.alexander.workout;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class WorkoutListActivity extends AppCompatActivity {

    private List<Exercise> exercises;
    private RecyclerView recView;
    private FloatingActionButton fab;
    private Toolbar workoutToolbar;
    private RecyclerViewAdapter adapter;
    ExerciseList exerciselist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        if (adapter != null && exerciselist != null)
            adapter.dataChanged(exerciselist.readExerciseFromDB());
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        setContentView(R.layout.activity_workout_list);
        exerciselist = new ExerciseList(this);
        exercises = exerciselist.readExerciseFromDB();

        recView = findViewById(R.id.recview);

        workoutToolbar = findViewById(R.id.workout_toolbar);
        setSupportActionBar(workoutToolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, AddExerciseActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(exercises);
        recView.setAdapter(adapter);

    }

}
