package com.example.cweather;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cweather.handler.TextHandler;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SelectReminder extends AppCompatActivity {
    private static final String TAG = "SelectReminder";
    private Toolbar toolbar;
    private CheckBox checkBoxInStart, checkBoxFiveMinute, checkBoxFifteenMinute, checkBoxThirtyMinute, checkBoxOneHour,
            checkBoxFourHour, checkBoxOneDay, checkBoxTwoDay, checkBoxOneWeek;
    private SwitchMaterial switchMaterial;

    private void initWidgets() {
        // Toolbar
        toolbar = findViewById(R.id.toolbarSelectRemineder);
        // SwitchMaterial
        switchMaterial = findViewById(R.id.switchNotReminder);
        // CheckBoxes
        checkBoxInStart = findViewById(R.id.checkBoxInStart);
        checkBoxFiveMinute = findViewById(R.id.checkBoxFiveMinute);
        checkBoxFifteenMinute = findViewById(R.id.checkBoxFifteenMinute);
        checkBoxThirtyMinute = findViewById(R.id.checkBoxThirtyMinute);
        checkBoxOneHour = findViewById(R.id.checkBoxOneHour);
        checkBoxFourHour = findViewById(R.id.checkBoxFourHour);
        checkBoxOneDay = findViewById(R.id.checkBoxOneDay);
        checkBoxTwoDay = findViewById(R.id.checkBoxTwoDay);
        checkBoxOneWeek = findViewById(R.id.checkBoxOneWeek);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_reminder, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_reminder);
        initWidgets();
        initToolbar();
        initCheckBox();
        // ???????????? activity
        checkBoxFiveMinute.setChecked(true);
        // ???????????? switch
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // ???????? ????????., ???? ?????? ?????? ?????????? ????????????????
                    checkBoxInStart.setEnabled(true);
                    checkBoxFiveMinute.setEnabled(true);
                    checkBoxFifteenMinute.setEnabled(true);
                    checkBoxThirtyMinute.setEnabled(true);
                    checkBoxOneHour.setEnabled(true);
                    checkBoxFourHour.setEnabled(true);
                    checkBoxOneDay.setEnabled(true);
                    checkBoxTwoDay.setEnabled(true);
                    checkBoxOneWeek.setEnabled(true);
                    //
                    updateTextHandler();
                } else {
                    // ???????? ??????., ???? ?????? ?????? ?????????? ????????????????????
                    TextHandler.setText("???? ????????????????????");
                    checkBoxInStart.setEnabled(false);
                    checkBoxFiveMinute.setEnabled(false);
                    checkBoxFifteenMinute.setEnabled(false);
                    checkBoxThirtyMinute.setEnabled(false);
                    checkBoxOneHour.setEnabled(false);
                    checkBoxFourHour.setEnabled(false);
                    checkBoxOneDay.setEnabled(false);
                    checkBoxTwoDay.setEnabled(false);
                    checkBoxOneWeek.setEnabled(false);
                }
            }
        });

    }

    private void initCheckBox() {
        checkBoxInStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxFiveMinute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxFifteenMinute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxThirtyMinute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxOneHour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxFourHour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxOneDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxTwoDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
        checkBoxOneWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTextHandler();
            }
        });
    }

    private void updateTextHandler() {
        TextHandler.setText("");
        if (checkBoxInStart.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ?? ???????????? ????????????");
        }
        if (checkBoxFiveMinute.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 5 ??????????");
        }
        if (checkBoxFifteenMinute.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 15 ??????????");
        }
        if (checkBoxThirtyMinute.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 30 ??????????");
        }
        if (checkBoxOneHour.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 1 ??????");
        }
        if (checkBoxFourHour.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 4 ????????");
        }
        if (checkBoxOneDay.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 1 ????????");
        }
        if (checkBoxTwoDay.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 2 ??????");
        }
        if (checkBoxOneWeek.isChecked()) {
            TextHandler.setText(TextHandler.getText() + ", ???? 1 ????????????");
        }
    }

    private void initToolbar() {
        // ?????????????????? toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }
}