package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExerciseViewHolder> {

    List<Exercise> exercises;

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
        final String exerciseText = exercises.get(position).getExerciseName();
        holder.exerciseName.setText(exerciseText);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
//                intent.putExtra("exerciseName", exerciseText);
//                intent.putExtra("exerciseDescription", )
                intent.putExtra("id", position); // передать порядковый номер в списке
//                Toast.makeText(context, exerciseText, Toast.LENGTH_SHORT).show();
                // пока что просто создается Тост для демонстнации что прога работает,
                // в будущем по клику будет подгружать инфа из бд и генериться активити
                context.startActivity(intent);
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

        ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseCard = itemView.findViewById(R.id.exercise_cardview);
            exerciseName = itemView.findViewById(R.id.exercise_name);
        }
    }
}
