package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class WorkoutListActivity extends AppCompatActivity {

    private List<Exercise> exercises;
    private RecyclerView recView;
    private FloatingActionButton fab;
    private Toolbar workoutToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initDB();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        recView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    private void initDB() {
        WorkoutDB sqh = new WorkoutDB(this);
        SQLiteDatabase database = sqh.getWritableDatabase();
        database.close();
        sqh.close();
    }

    private void initUI() {
        setContentView(R.layout.activity_workout_list);
        exercises = ExerciseList.getExercisesList();
        recView = findViewById(R.id.recview);

        workoutToolbar = findViewById(R.id.workout_toolbar);
        setSupportActionBar(workoutToolbar); // инициализируем ActionBar

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
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(exercises);
        recView.setAdapter(adapter);
    }

}
