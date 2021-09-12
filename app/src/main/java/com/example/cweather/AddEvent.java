package com.example.cweather;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cweather.database.Event;
import com.example.cweather.database.EventBase;
import com.example.cweather.handler.TextHandler;
import com.example.cweather.remote.DataCallBack;
import com.example.cweather.remote.DataManager;
import com.google.android.material.textfield.TextInputLayout;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.jaredrummler.android.colorpicker.ColorShape;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddEvent extends AppCompatActivity implements ColorPickerDialogListener, DataCallBack {
    private static final String TAG = "TheFoxLogs";
    private static final String FORMAT = "dd MMMM yyyy HH:mm";
    public String date = "";
    public Calendar now;
    int color_back = Color.RED;
    private TextInputLayout inputEvent, inputPlace, inputDecs;
    private LinearLayout btnStart, btnEnd, btnSelectRem, btnSelectColor;
    private Toolbar toolbar;
    private TextView textStart, textEnd, textSelectRem, textSelectColor;
    private DataManager dataManager;

    public static String formatDateToString(long date) {
        // Из миллисекунд в строковую дату
        Locale locale = new Locale("ru");
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT, locale);
        return dateFormat.format(date);
    }

    public static String addHourMinute(String date, int hour, int minute) throws ParseException {
        // Добавить к строке часы и минуты
        Locale locale = new Locale("ru");
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT, locale);
        Calendar calendar = Calendar.getInstance();
        Date selectedDate = dateFormat.parse(date);
        assert selectedDate != null;
        calendar.setTime(selectedDate);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minute);
        return dateFormat.format(calendar.getTime());
    }

    private void initToolbar() {
        // Настройка toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initWidgets() {
        // Buttons
        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        btnSelectRem = findViewById(R.id.btnSelectRem);
        btnSelectColor = findViewById(R.id.btnSelectColor);
        // Toolbar
        toolbar = findViewById(R.id.toolbarEvent);
        // Inputs
        inputEvent = findViewById(R.id.inputEvent);
        inputPlace = findViewById(R.id.inputPlace);
        inputDecs = findViewById(R.id.inputDesc);
        // TextView
        textStart = findViewById(R.id.textStart);
        textEnd = findViewById(R.id.textEnd);
        textSelectRem = findViewById(R.id.textSelectRem);
        textSelectColor = findViewById(R.id.textSelectColor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Инициализцаия меню
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Логика кнопок меню
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.actionCreate:
                checkFields();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkFields() {
        // Проверка всех полей
        if (inputEvent.getEditText().getText().toString().isEmpty()) {
            inputEvent.setError("Поле не должно быть пустым");
            return;
        } else {
            inputEvent.setError(null);
        }
        if (!Objects.requireNonNull(formatStringToDate(textStart.getText().toString())).before(formatStringToDate(textEnd.getText().toString()))) {
            btnStart.setBackgroundResource(R.drawable.customborder);
            btnEnd.setBackgroundResource(R.drawable.customborder);
            Toast.makeText(AddEvent.this, "Время начала должно быть раньше, чем время окончания", Toast.LENGTH_SHORT).show();
            return;
        } else {
            btnStart.setBackgroundResource(0);
            btnEnd.setBackgroundResource(0);
        }
        if (inputPlace.getEditText().getText().toString().isEmpty()) {
            inputPlace.setError("Поле не должно быть пустым");
            return;
        } else {
            inputPlace.setError(null);
        }
        if (inputDecs.getEditText().getText().toString().isEmpty()) {
            inputDecs.setError("Поле не должно быть пустым");
            return;
        } else {
            inputDecs.setError(null);
        }
        addEvent();

    }

    private void addEvent() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatStringToDate(date));
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(formatStringToDate(textStart.getText().toString()));
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(formatStringToDate(textEnd.getText().toString()));
//        dataManager.addData(AddEvent.this, calendar, startTime, endTime, inputEvent.getEditText().getText().toString(),
//                inputPlace.getEditText().getText().toString(), inputDecs.getEditText().getText().toString(),
//                textSelectRem.getText().toString(), color_back);
        startActivity(new Intent(AddEvent.this, MainActivity.class));
        String name = inputEvent.getEditText().getText().toString();
        String place = inputPlace.getEditText().getText().toString();
        String desc = inputDecs.getEditText().getText().toString();
        String reminder = textSelectRem.getText().toString();
        int color = color_back;
        dataManager.addData(AddEvent.this, calendar, startTime, endTime, name, place, desc, reminder, color);


    }

    private void createColorPickerDialog() {
        ColorPickerDialog.Builder builder = ColorPickerDialog.newBuilder();
        builder.setColor(Color.RED);
        builder.setDialogType(ColorPickerDialog.TYPE_PRESETS);
        builder.setAllowPresets(true);
        builder.setAllowCustom(true);
        builder.setColorShape(ColorShape.SQUARE);
        builder.setDialogTitle(R.string.chooseColor);
        builder.setDialogId(100);
        builder.show(this);


    }

    private Date formatStringToDate(String date) {
        Locale locale = new Locale("ru");
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT, locale);
        try {
            Date date1 = dateFormat.parse(date);
            return date1;

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        dataManager = new DataManager(this);
        // Инициализация объектов activity
        initWidgets();
        initToolbar();
        // Получение даты
        long selectedDate = getIntent().getLongExtra("selectedDate", -1);
        date = formatDateToString(selectedDate);
        now = Calendar.getInstance();
        // Кнопка, начало мероприятия
        textStart.setText(date);
        btnStart.setOnClickListener(new View.OnClickListener() {
            final TimePickerDialog.OnTimeSetListener setStartTime = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    try {
                        textStart.setText(addHourMinute(date, hourOfDay, minute));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            };

            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEvent.this, setStartTime, now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), true).show();
            }
        });
        // Кнопка, окончание мероприятия
        textEnd.setText(date);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            final TimePickerDialog.OnTimeSetListener setEndTime = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    try {
                        textEnd.setText(addHourMinute(date, hourOfDay, minute));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            };

            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddEvent.this, setEndTime, now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE), true).show();
            }
        });
        // Кнопка, напоминания
        btnSelectRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEvent.this, SelectReminder.class));
            }
        });
        // Кнопка, выбора цвета
        btnSelectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createColorPickerDialog();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (TextHandler.getText() != null) {
            textSelectRem.setText(TextHandler.getText().replaceFirst(",", "").trim());
        }
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        textSelectColor.setBackgroundColor(color);
        color_back = color;
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }

    @Override
    public void dataAdded() {
        Log.d(TAG, "Успешно создана запись в бд");

    }

    @Override
    public void errorAdded() {
        Log.d(TAG, "Не создана запись в бд");
    }

    @Override
    public void loadEventsBase(List<EventBase> events) {

    }

}