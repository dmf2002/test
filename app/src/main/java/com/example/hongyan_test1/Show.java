package com.example.hongyan_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import http.SentHttp;

public class Show extends AppCompatActivity {
    private List<Event> eventList=new ArrayList<>();
    private SentHttp sentHttp=new SentHttp();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_demo);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        EventAdapter eventAdapter=new EventAdapter(eventList);
        recyclerView.setAdapter(eventAdapter);
        
    }
    }
