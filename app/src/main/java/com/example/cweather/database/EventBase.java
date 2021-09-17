package com.example.cweather.database;

import androidx.room.ColumnInfo;


public class EventBase {
    @ColumnInfo(name = "dateCalendar")
    public String dateCalendar;

    @ColumnInfo(name = "color")
    public int color;
}
