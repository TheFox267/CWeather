package com.example.cweather.model;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import com.example.cweather.converters.CalendarConverter;

import java.util.Calendar;

public class BasedInformationDate {
    @ColumnInfo(name = "calendar")
    @TypeConverters({CalendarConverter.class})
    public Calendar calendar;

    @ColumnInfo(name = "color")
    public int color;
}
