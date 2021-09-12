package com.example.cweather.remote;

import com.example.cweather.database.Event;
import com.example.cweather.database.EventBase;

import java.util.List;

public interface DataCallBack {
    void dataAdded();
    void errorAdded();

    void loadEventsBase(List<EventBase> events);
}
