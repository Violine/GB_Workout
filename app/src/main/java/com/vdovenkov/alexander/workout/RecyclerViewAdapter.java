package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ExerciseViewHolder> implements WorkoutConstants {

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
    public void onBindViewHolder(final ExerciseViewHolder holder, int position) {
        Picasso.get().load("http://karemfitlife.com/wp-content/uploads/2016/05/111-60x60.png?e94a18").into(holder.exerciseImage);
        String exerciseName = exercises.get(position).getExerciseName();
        String exerciseRecordDate = exercises.get(position).getExerciseRecordDate();
        String exerciseDescription = exercises.get(position).getExerciseDescription();
        int exerciseRecord = exercises.get(position).getExerciseRecord();
        holder.exerciseDescription.setText(exerciseDescription);
        holder.exerciseRecordDate.setText(exerciseRecordDate);
        holder.exerciseRecord.setText(String.valueOf(exerciseRecord));
        holder.exerciseName.setText(exerciseName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, WorkoutDetailActivity.class);
                intent.putExtra("id", holder.getAdapterPosition()); // передать порядковый номер в списке
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()

        {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog dialog = CustomDialog.getDialog(v.getContext(), REMOVE_DIALOG, holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void dataChanged(List<Exercise> newExercisesList) {
        exercises = newExercisesList;
        notifyDataSetChanged();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {

        CardView exerciseCard;
        TextView exerciseName;
        TextView exerciseRecord;
        TextView exerciseRecordDate;
        TextView exerciseDescription;
        ImageView exerciseImage;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseCard = itemView.findViewById(R.id.exercise_cardview);
            exerciseName = itemView.findViewById(R.id.exercise_name);
            exerciseRecord = itemView.findViewById(R.id.workout_detail_record_label_cardview);
            exerciseImage = itemView.findViewById(R.id.exercise_card_image);
            exerciseRecordDate = itemView.findViewById(R.id.workout_detail_date_label_cardview);
            exerciseDescription = itemView.findViewById(R.id.workout_detail_card_description);
        }
    }
}
