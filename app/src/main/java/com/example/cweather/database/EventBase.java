package com.example.cweather.database;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import com.example.cweather.converters.Converter;

import java.util.Calendar;

public class EventBase {
    @ColumnInfo(name = "date")
    @TypeConverters({Converter.class})
    public Calendar date;

    @ColumnInfo(name = "color")
    public int color;
}
