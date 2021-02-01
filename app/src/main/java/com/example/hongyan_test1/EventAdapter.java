package com.example.hongyan_test1;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> mEventlist;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView yeras, monthday, type, desc1, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            yeras=(TextView)itemView.findViewById(R.id.tv_years);
            monthday=(TextView)itemView.findViewById(R.id.tv_monthday);
            type=(TextView)itemView.findViewById(R.id.tv_type);
            desc1=(TextView)itemView.findViewById(R.id.tv_desc1);
            title=(TextView)itemView.findViewById(R.id.tv_title);
        }
    }
    public EventAdapter(List<Event> eventList) {
        mEventlist=eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Event event=mEventlist.get(position);
         holder.yeras.setText(event.getYears());
         holder.monthday.setText(event.getMonthday());
         holder.desc1.setText(event.getDesc1());
         holder.type.setText(event.getType());
         holder.title.setText(event.getType());
    }

    @Override
    public int getItemCount() {
        return mEventlist.size();
    }
}
