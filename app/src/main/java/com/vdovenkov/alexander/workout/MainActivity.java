package com.vdovenkov.alexander.workout;


import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageButton plusRepsButton;
    ImageButton minusRepsButton;
    TextView repsCountTextView;
    TextView weightTextView;
    SeekBar weightSeekBar;
    Button saveRecordButton;
    TextView recordValueTextView;
    TextView recordDateTextView;
    String currentDate;
    int reps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repsCountTextView = findViewById(R.id.workout_detail_reps_text_view);
        weightTextView = findViewById(R.id.workout_detail_weight_text_view);
        recordValueTextView = findViewById(R.id.workout_detail_record_value_text_view);
        recordDateTextView = findViewById(R.id.workout_detail_record_date);

        reps = Integer.parseInt(repsCountTextView.getText().toString());

        plusRepsButton = findViewById(R.id.workout_detail_plus_reps_button);
        minusRepsButton = findViewById(R.id.workout_detail_minus_reps_button);
        saveRecordButton = findViewById(R.id.workout_detail_save_record_button);

        weightSeekBar = findViewById(R.id.workout_detail_weight_seek_bar);


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
                SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.YYYY");
                currentDate = formattedDate.format(new Date());
                recordDateTextView.setText("Дата: " + currentDate);
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


    }
}
