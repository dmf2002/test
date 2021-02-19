package com.example.hongyan_test1;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import db.MyDatabaseHelper;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Event> mEventlist;
    private Context mcontext;
    private MainActivity mainActivity;
    private MyDatabaseHelper dbHelper;
    private  int mSign1;
    private EventAdapter meventAdapter;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView yeras, monthday, type, desc1, title;
        View eventview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventview=itemView;
            yeras=(TextView)itemView.findViewById(R.id.tv_years);
            monthday=(TextView)itemView.findViewById(R.id.tv_monthday);
            type=(TextView)itemView.findViewById(R.id.tv_type);
            desc1=(TextView)itemView.findViewById(R.id.tv_desc1);
            title=(TextView)itemView.findViewById(R.id.tv_title);
        }
    }
    public EventAdapter(List<Event> eventList,Context context,int sign) {
        mEventlist=eventList;
        mcontext=context;
        mSign1=sign;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
         ViewHolder holder = new ViewHolder(view);
        dbHelper = new MyDatabaseHelper(mcontext, "PostThing.db", null, 1);
        holder.eventview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Event event = mEventlist.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
                if(mSign1==1) {
                    dialog.setMessage("是否保存");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("years", event.getYears());
                            values.put("monthday", event.getMonthday());
                            values.put("title", event.getTitle());
                            values.put("desc1", event.getDesc1());
                            values.put("type", event.getType());
                            db.insert("Event", null, values);
                            Cursor cursor = db.query("Event", null, null, null, null, null, null);
                            if (cursor.moveToFirst()) {
                                do {
                                    String desc = cursor.getString(cursor.getColumnIndex("desc1"));
                                } while (cursor.moveToNext());
                            }
                            cursor.close();
                        }
                    });
                    dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();
                }
                else {
                        dialog.setMessage("是否删除");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.delete("Event","desc1==?",new String[] {event.getDesc1()});
                                mEventlist.remove(position);
                                EventAdapter.this.notifyItemRemoved(position);
                                notifyItemRangeChanged(position,mEventlist.size());
                            }

                        });
                        dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                }
            }
        });
            return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Event event=mEventlist.get(position);
         holder.yeras.setText(event.getYears());
         holder.monthday.setText(event.getMonthday());
         holder.desc1.setText(event.getDesc1());
         holder.type.setText(event.getType());
         holder.title.setText(event.getTitle());
    }

    @Override
    public int getItemCount() {
        return mEventlist.size();
    }
}
