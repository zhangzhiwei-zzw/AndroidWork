package com.test.work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Mine extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my);
        ImageView index=(ImageView)findViewById(R.id.index);
        ImageView find=(ImageView)findViewById(R.id.find);
        ImageView mine=(ImageView)findViewById(R.id.mine);
        TextView login=(TextView)findViewById(R.id.login);

        ImageView userimg=(ImageView)findViewById(R.id.userimg);
        TextView username=(TextView)findViewById(R.id.username);
        TextView userword=(TextView)findViewById(R.id.userword);

        SharedPreferences preferences;
        preferences=getSharedPreferences("Register",MODE_PRIVATE);

        String name=preferences.getString("name","用户未登录");
        String word=preferences.getString("userword","。。。。。。");
        int imageid=preferences.getInt("imageId",R.drawable.tubiao9);

        userimg.setImageResource(imageid);
        username.setText("用户昵称："+name);
        userword.setText("个性签名："+word);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mine.this,Login.class);
                startActivity(intent);
            }
        });

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mine.this,com.test.work.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Mine.this,com.test.work.Find.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
