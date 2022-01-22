package com.test.work;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Pojo extends Activity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feiyixiangmu);
        ImageView ruci=(ImageView)findViewById(R.id.ruci);
        ImageView huaci=(ImageView)findViewById(R.id.huaci);
        ImageView tangsancai=(ImageView)findViewById(R.id.tangsanci);
        ImageView juci=(ImageView)findViewById(R.id.junci);

        ruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pojo.this,Ruci.class);
                startActivity(intent);
            }
        });
        huaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pojo.this,Huaci.class);
                startActivity(intent);
            }
        });
        tangsancai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pojo.this,Tansancai.class);
                startActivity(intent);
            }
        });
        juci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Pojo.this,Junci.class);
                startActivity(intent);
            }
        });

    }
}
