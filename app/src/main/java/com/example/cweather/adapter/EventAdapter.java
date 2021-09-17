package com.example.cweather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cweather.R;
import com.example.cweather.database.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> eventList = new ArrayList<>();

    public void setEventList(Collection<Event> events) {
        eventList.addAll(events);
        notifyDataSetChanged();
    }

    public void clearEventList() {
        eventList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(eventList.get(position));

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textName, textTime, textPlace;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textNameItem);
            textTime = itemView.findViewById(R.id.textTimeItem);
            textPlace = itemView.findViewById(R.id.textPlaceItem);
        }

        public void bind(Event event) {
            textName.setText(event.name);
            textTime.setText(event.timeStart + " - " + event.timeEnd);
            textPlace.setText(event.place);

        }
    }
}
