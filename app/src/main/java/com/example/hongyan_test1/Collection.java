package com.example.hongyan_test1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import db.MyDatabaseHelper;

public class Collection extends AppCompatActivity {
    private  MyDatabaseHelper myDatabaseHelper;
    private List<Event> eventlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        myDatabaseHelper = new MyDatabaseHelper(this, "PostThing.db", null, 1);
        EventAdapter eventAdapter = new EventAdapter(eventlist, this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_demo);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(eventAdapter);
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query("Event", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String type = cursor.getString(cursor.getColumnIndex("type"));
                String years = cursor.getString(cursor.getColumnIndex("years"));
                String monthday = cursor.getString(cursor.getColumnIndex("monthday"));
                String desc1 = cursor.getString(cursor.getColumnIndex("desc1"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                eventlist.add(new Event(years, monthday, title, desc1, type));
            } while (cursor.moveToNext());

        }
        cursor.close();
    }

}

