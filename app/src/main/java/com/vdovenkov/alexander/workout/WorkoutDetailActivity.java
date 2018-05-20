package com.vdovenkov.alexander.workout;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WorkoutDetailActivity extends AppCompatActivity {

    ImageButton plusRepsButton;
    ImageButton minusRepsButton;
    ImageButton shareResultButton;
    Button saveRecordButton;
    FloatingActionButton removeExerciseFab;

    TextView repsCountTextView;
    TextView weightTextView;
    TextView recordValueTextView;
    TextView recordDateTextView;
    TextView titleTextView;

    SeekBar weightSeekBar;
    Toolbar workoutToolbar;

    String currentDate;
    int reps;
    String toShareMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitUI();

        plusRepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repsCountTextView.setText(String.valueOf(++reps));
            }
        });
        minusRepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reps >= 1) {
                    repsCountTextView.setText(String.valueOf(--reps));
                } else {
                    repsCountTextView.setText("0");
                }
            }
        });

        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordValueTextView.setText(MessageFormat.format(getString(R.string.record), weightSeekBar.getProgress(), repsCountTextView.getText()));
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.YYYY", Locale.ROOT);
                currentDate = formattedDate.format(new Date());
                recordDateTextView.setText(getString(R.string.date) + currentDate);
            }
        });
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weightTextView.setText(MessageFormat.format(getString(R.string.weight), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        shareResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toShareMessage = MessageFormat.format(getString(R.string.shared_message), recordValueTextView.getText().toString(), recordDateTextView.getText().toString());
                createShareIntent(toShareMessage);
            }
        });
        removeExerciseFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle(getString(R.string.delete_exercise_warning));
                builder.setMessage(getString(R.string.message_delete_warning));
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(), "Удаляем элемент", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    private void createShareIntent(String messageToShare) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, messageToShare);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_resut)));
    }

    private void InitUI() {
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String titleText = intent.getStringExtra("exerciseName");

        workoutToolbar = findViewById(R.id.workout_toolbar);
        repsCountTextView = findViewById(R.id.workout_detail_reps_text_view);
        weightTextView = findViewById(R.id.workout_detail_weight_text_view);
        recordValueTextView = findViewById(R.id.workout_detail_record_value_text_view);
        recordDateTextView = findViewById(R.id.workout_detail_record_date);
        titleTextView = findViewById(R.id.workout_detail_title_text_view);
        titleTextView.setText(titleText);

        reps = Integer.parseInt(repsCountTextView.getText().toString());

        shareResultButton = findViewById(R.id.workout_detail_share_result_button);
        plusRepsButton = findViewById(R.id.workout_detail_plus_reps_button);
        minusRepsButton = findViewById(R.id.workout_detail_minus_reps_button);
        saveRecordButton = findViewById(R.id.workout_detail_save_record_button);
        removeExerciseFab = findViewById(R.id.fab_remove_exercise);

        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);

        setSupportActionBar(workoutToolbar);
    }
}
