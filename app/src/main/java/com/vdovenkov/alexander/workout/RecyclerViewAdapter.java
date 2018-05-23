package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExerciseViewHolder> implements WorkoutConstants {

    List<Exercise> exercises;
    WorkoutDB sqlHelper;
    SQLiteDatabase workoutDB;

    RecyclerViewAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card, parent, false);
        ExerciseViewHolder exerViewHolder = new ExerciseViewHolder(v);
        return exerViewHolder;
    }

    @Override
    public void onBindViewHolder(ExerciseViewHolder holder, final int position) {

//        sqlHelper = new WorkoutDB(holder.exerciseCard.getContext());
//        workoutDB = sqlHelper.getWritableDatabase();
//        String recordDBTest;
//        Cursor cursor = workoutDB.query(WorkoutDB.TABLE_NAME, null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            int recordColumnIndex = cursor.getColumnIndex(WorkoutDB.WORKOUT_RECORD);
//            recordDBTest = cursor.getString(recordColumnIndex);
//            holder.exerciseRecord.setText(recordDBTest);
//        }

        final String exerciseText = exercises.get(position).getExerciseName();
        holder.exerciseName.setText(exerciseText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra("id", position); // передать порядковый номер в списке
                //intent.putExtra("record", recordDBTest); // передаем тестовое значение из БД
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()

        {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = CustomDialog.getDialog(v.getContext(), REMOVE_DIALOG, position);
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }


    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        CardView exerciseCard;
        TextView exerciseName;
        TextView exerciseRecord;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseCard = itemView.findViewById(R.id.exercise_cardview);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseRecord = itemView.findViewById(R.id.workout_detail_record_label_cardview);
        }
    }
}
