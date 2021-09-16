package com.example.cweather.converters;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Converter {
    private final String FORMAT = "dd MMMM yyyy HH:mm";
    private final Locale LOCALE = new Locale("ru");
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT, LOCALE);

    /**
     * Конвертировать из Calendar в long
     */
    public static Long fromCalendarToLong(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    /**
     * Конвертировать из long в Calendar
     */
    public static Calendar fromLongToCalendar(Long data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        return calendar;
    }

    /**
     * Конвертировать из Date в Calendar
     */
    public Calendar fromDateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Конвертировать из Calendar в Date
     */
    public Date fromCalendarToDate(@NonNull Calendar calendar) {
        return calendar.getTime();
    }

    /**
     * Конвертировать из String в Date
     */
    public Date fromStringToDate(String str) throws ParseException {
        return simpleDateFormat.parse(str);
    }

    /**
     * Конвертировать из Calendar в String
     */
    public String fromCalendarToString(Calendar calendar) {
        return simpleDateFormat.format(fromCalendarToDate(calendar));
    }

    /**
     * Конвертировать из String в Calendar
     */
    public Calendar fromStringToCalendar(String str) throws ParseException {
        Date date = simpleDateFormat.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Конвертировать из long в String
     */
    public String fromLongToString(long date) {
        return simpleDateFormat.format(date);
    }

}
