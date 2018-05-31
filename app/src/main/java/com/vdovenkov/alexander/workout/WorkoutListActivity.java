package com.vdovenkov.alexander.workout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class WorkoutListActivity extends Fragment {
    private static final String TAG = "WorkoutListActivity";

    private List<Exercise> exercises;
    private RecyclerView recView;
    private FloatingActionButton fab;
    private RecyclerViewAdapter adapter;
    ExerciseList exerciselist;
    Toolbar workoutToolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_workout_list, container, false);
        exerciselist = new ExerciseList(getActivity());
        exercises = exerciselist.readExerciseFromDB();
        recView = root.findViewById(R.id.recview);
        fab = root.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddExerciseActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerViewAdapter(exercises);
        recView.setAdapter(adapter);

        workoutToolbar = root.findViewById(R.id.workout_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(workoutToolbar);
        workoutToolbar.setTitle(R.string.workout_application);

        return root;
    }

    @Override
    public void onResume() {
        if (adapter != null && exerciselist != null)
            adapter.dataChanged(exerciselist.readExerciseFromDB());
        super.onResume();
    }
}
