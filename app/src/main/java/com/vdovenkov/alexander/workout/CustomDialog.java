package com.vdovenkov.alexander.workout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;


public class CustomDialog {
    private static final int REMOVE_DIALOG = 1;
    private static final int OTHER_DIALOG = 2; // вдруг понадобятся еще диалоги - будем использовать этот ID

    public static AlertDialog getDialog(final Context context, int dialogID, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        switch (dialogID) {
            case REMOVE_DIALOG:
                builder.setTitle(R.string.delete_exercise_warning);
                builder.setMessage(R.string.message_delete_warning);
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExerciseList.removeExerciseFromList(id);
                        Intent intent = new Intent(context, WorkoutListActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        }
        builder.setCancelable(true);
        return builder.create();
    }

}


