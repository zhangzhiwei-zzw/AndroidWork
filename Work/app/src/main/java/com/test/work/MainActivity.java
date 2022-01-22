package com.test.work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView index=(ImageView)findViewById(R.id.index);
        ImageView find=(ImageView)findViewById(R.id.find);
        ImageView mine=(ImageView)findViewById(R.id.mine);
        ImageView pojo=(ImageView)findViewById(R.id.pojo);

        pojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Pojo.class);
                startActivity(intent);
            }
        });


        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,com.test.work.Mine.class);
                startActivity(intent);
                finish();
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,com.test.work.Find.class);
                startActivity(intent);
                finish();
            }
        });
    }
}