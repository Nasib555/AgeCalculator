package com.lucidsws.agecalculator;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class dialog extends AppCompatDialogFragment {
    private static final String TAG = "dialog";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year, month, day;
        try {
            if (getTag().equals("birth")) {
                c.set(2000, 1, 1);
                Log.d(TAG, "this is from birthday Button");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "onCreateDialog: ERROR");
        }

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog picker = new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener) getActivity(),
                year, month, day);

        picker.getDatePicker().setTag(this.getTag());
        return picker;
    }

}
