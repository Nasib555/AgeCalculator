package com.lucidsws.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    TextView curDate, birthDate, ageResult;
    Button pickCur, pickBirthday;
    LocalDate curdateObject, birthdayObject;
    Calendar calendarDefault = Calendar.getInstance();
    AdView adview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curDate = findViewById(R.id.editCurDate);
        birthDate = findViewById(R.id.editBirthDate);
        ageResult = findViewById(R.id.ageResult);

        pickCur = findViewById(R.id.btnPickCurDate);
        pickBirthday = findViewById(R.id.btnPickBirthDate);

        String curDefault = DateFormat.getDateInstance().format(calendarDefault.getTime());
        curDate.setText(curDefault);

        curdateObject = LocalDate.of(
                calendarDefault.get(Calendar.YEAR),
                calendarDefault.get(Calendar.MONTH),
                calendarDefault.get(Calendar.DAY_OF_MONTH));

        pickCur.setOnClickListener(this);
        pickBirthday.setOnClickListener(this);

        adview = findViewById(R.id.banner_id);
        MobileAds.initialize(this);
        adview.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onClick(View v) {
        dialog mydialog = new dialog();
        switch (v.getId()) {
            case R.id.btnPickCurDate:
                mydialog.show(getSupportFragmentManager(), "current");
                break;
            case R.id.btnPickBirthDate:
                mydialog.show(getSupportFragmentManager(), "birth");
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        String dateForm = DateFormat.getDateInstance().format(c.getTime());
        if (view.getTag().equals("birth")) {
            birthDate.setText(dateForm);

            birthdayObject = LocalDate.of(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            setResult(birthdayObject, curdateObject);

        } else if (view.getTag().equals("current")) {
            curDate.setText(dateForm);

            curdateObject = LocalDate.of(
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH));
            //call the method to calculate difference
            setResult(birthdayObject, curdateObject);
        }

    }

    private void setResult(LocalDate date1, LocalDate date2) {
        if (date1 != null && date2 != null) {

            Period period = Period.between(date1, date2);
            ageResult.setText("you are\n\n" + period.getYears() + "  years\n" + period.getMonths() + "  months\n"
                    + "and\n" + period.getDays() + "  days\n\nOLD"
            );
        }
    }

    public void getsource(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/open?id=1T0mUDTrKF5yM-XWR1sVGe5SK2TEwHN2p"));
        startActivity(i);

    }
}
