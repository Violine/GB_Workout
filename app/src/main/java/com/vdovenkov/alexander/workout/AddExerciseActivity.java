package com.vdovenkov.alexander.workout;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExerciseActivity extends AppCompatActivity {
    Button addExerciseButton;
    Button closeAddExerciseActivityButton;

    EditText exerciseNameEditText;
    EditText exerciseDescriptionEditText;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        initUI();
    }
    private void initDB() {
        WorkoutDB sqh = new WorkoutDB(this);
        database = sqh.getWritableDatabase();
        database.close();
        sqh.close();
    }

    private void initUI() {
        addExerciseButton = findViewById(R.id.workout_detail_accept_add_exercise_button);
        closeAddExerciseActivityButton = findViewById(R.id.workout_detail_cancel_add_exercise_button);
        exerciseNameEditText = findViewById(R.id.workout_detail_enter_exercise_name);
        exerciseDescriptionEditText = findViewById(R.id.workout_detail_enter_exercise_description);

        closeAddExerciseActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newExerciseName = exerciseNameEditText.getText().toString();
                String newExerciseDescription = exerciseDescriptionEditText.getText().toString();
                initDB();
                ContentValues content = new ContentValues();
                if (!newExerciseName.equals("")) {
                    if (newExerciseDescription.equals("")) {
                        ExerciseList.addExerciseToList(new Exercise(newExerciseName));
                        content.put(WorkoutDB.WORKOUT_NAME, newExerciseName);
                        database.insert(WorkoutDB.TABLE_NAME, WorkoutDB.WORKOUT_NAME, content);
                    } else {
                        ExerciseList.addExerciseToList(new Exercise(newExerciseName, newExerciseDescription));
                    }
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "Введите название упражнения!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
