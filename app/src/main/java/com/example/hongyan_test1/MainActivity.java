package com.example.hongyan_test1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import db.MyDatabaseHelper;
import http.SentHttp;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private List<Event> eventList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.btn_seek);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Show.class);
                startActivity(intent);
            }
        });
        dbHelper=new MyDatabaseHelper(this,"PostThing.db",null,1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case R.id.status_item:
                Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(intent);
                break;
            default:
        }
        return true;
    }
}