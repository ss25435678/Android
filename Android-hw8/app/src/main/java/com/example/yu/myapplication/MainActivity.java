package com.example.yu.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText editDate;
    private EditText editCost;
    private StringBuilder stringBuilder;
    private Formatter formatter;
    private ArrayList<String> dataList;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        editDate = findViewById(R.id.editDate);
        editDate.setEnabled(false);
        editCost = findViewById(R.id.editCost);
        ((DatePicker) findViewById(R.id.dpkDataPicker)).setOnDateChangedListener(dpkDatePicker_OnDateChanged);
        ((CalendarView) findViewById(R.id.cldCalendar)).setOnDateChangeListener(cldCalendar_OnDateChanged);
        findViewById(R.id.inputButton).setOnClickListener(inputButton_OnClick);
        findViewById(R.id.recordButton).setOnClickListener(recordButton_OnClick);
        stringBuilder = new StringBuilder();
        formatter = new Formatter(stringBuilder, Locale.TAIWAN);
        dataList = new ArrayList<>();
        number = 0;
    }
    private final DatePicker.OnDateChangedListener dpkDatePicker_OnDateChanged = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String date = year + "/" + (monthOfYear + 1) + "/" + dayOfMonth;
            editDate.setText(date);
        }
    };
    private final CalendarView.OnDateChangeListener cldCalendar_OnDateChanged = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            String date = year + "/" + (month + 1) + "/" + dayOfMonth;
            editDate.setText(date);
        }
    };
    private final View.OnClickListener inputButton_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String itemName = spinner.getSelectedItem().toString();
            String date = editDate.getText().toString();
            String cost = editCost.getText().toString();
            stringBuilder.delete(0, stringBuilder.length());
            formatter.format("項目%d  %10s  %10s  %10s", number++, date, itemName, cost);
            dataList.add(stringBuilder.toString());
            Toast.makeText(MainActivity.this, cost, Toast.LENGTH_SHORT).show();
        }
    };

    private final View.OnClickListener recordButton_OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, RecordActivity.class);
            intent.putStringArrayListExtra("dataList", dataList);
            startActivity(intent);
        }
    };

}
