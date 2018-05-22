package com.example.yu.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
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
    private ConstraintLayout constraintLayout;

    private static final int Menu_Music = Menu.FIRST,
            Menu_Play_Music = Menu.FIRST + 1,
            Menu_Stop_Playing_Music = Menu.FIRST + 2,
            Menu_About = Menu.FIRST + 3,
            Menu_Exit = Menu.FIRST + 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        editDate = findViewById(R.id.editDate);
        editDate.setEnabled(false);
        editCost = findViewById(R.id.editCost);
        constraintLayout = findViewById(R.id.constraintLayout);
        ((DatePicker) findViewById(R.id.dpkDataPicker)).setOnDateChangedListener(dpkDatePicker_OnDateChanged);
        ((CalendarView) findViewById(R.id.cldCalendar)).setOnDateChangeListener(cldCalendar_OnDateChanged);
        findViewById(R.id.inputButton).setOnClickListener(inputButton_OnClick);
        findViewById(R.id.recordButton).setOnClickListener(recordButton_OnClick);
        stringBuilder = new StringBuilder();
        formatter = new Formatter(stringBuilder, Locale.TAIWAN);
        dataList = new ArrayList<>();
        number = 0;
        registerForContextMenu(constraintLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu(0, Menu_Music, 0, "背景音樂").setIcon(android.R.drawable.ic_media_ff);
        subMenu.add(0, Menu_Play_Music, 0, "播放背景音樂");
        subMenu.add(0, Menu_Stop_Playing_Music, 1, "停止播放背景音樂");
        menu.add(0, Menu_About, 1, "關於這個程式...").setIcon(android.R.drawable.ic_dialog_info);
        menu.add(0, Menu_Exit, 2, "結束").setIcon(android.R.drawable.ic_menu_close_clear_cancel);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu_Play_Music:
                Intent it = new Intent(MainActivity.this, MediaService.class);
                startService(it);
                return true;
            case Menu_Stop_Playing_Music:
                it = new Intent(MainActivity.this, MediaService.class);
                stopService(it);
                return true;
            case Menu_About:
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("關於這個程式")
                        .setMessage("NTUT-CSIE 106-2 Android Hw9 105590037")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.star_big_on)
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();

                return true;
            case Menu_Exit:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == constraintLayout) {
            if (menu.size() == 0) {
                SubMenu subMenu = menu.addSubMenu(0, Menu_Music, 0, "背景音樂");
                subMenu.add(0, Menu_Play_Music, 0, "播放背景音樂");
                subMenu.add(0, Menu_Stop_Playing_Music, 1, "停止播放背景音樂");
                menu.add(0, Menu_About, 1, "關於這個程式...");
                menu.add(0, Menu_Exit, 2, "結束");
            }
        }
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
