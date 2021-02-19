package com.example.hongyan_test1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import http.SentHttp;

public class Show extends AppCompatActivity {
    private List<Event> eventList=new ArrayList<>();
    EventAdapter eventAdapter = new EventAdapter(eventList,Show.this,1);
    SentHttp sentHttp=new SentHttp();

    public Show() throws NoSuchMethodException{
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        String data=intent.getStringExtra("monthday");
        setContentView(R.layout.activity_show);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_demo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(eventAdapter);
      sentHttp.sendPostNetRequest("https://v1.alapi.cn/api/eventHitory",eventList,eventAdapter,data);

    }
    }

