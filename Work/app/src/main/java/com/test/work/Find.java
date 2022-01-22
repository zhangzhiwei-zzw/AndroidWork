package com.test.work;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Find extends AppCompatActivity {

    String[] username=new String[]{"zzz","zzw","zzu"};
    String[] comments=new String[]{"11111","22222","33333"};
    String[] time=new String[]{"2019-11-11","2020-11-22","2021-12-12"};
    String[] position=new String[]{"河南郑州","河南郑州","河南郑州"};
    String[] imageid=new String[]{"2131165394","2131165394","2131165394"};

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guangchang);
        ImageView index=(ImageView)findViewById(R.id.index);
        ImageView find=(ImageView)findViewById(R.id.find);
        ImageView mine=(ImageView)findViewById(R.id.mine);
        ListView list=(ListView)findViewById(R.id.mylist);
        ImageView add=(ImageView)findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences;
                preferences=getSharedPreferences("Register",MODE_PRIVATE);
                String name=preferences.getString("name","");
                if(name.equals(""))
                {
                    Toast.makeText(Find.this,"用户未登录",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent=new Intent(Find.this,Add.class);
                    startActivity(intent);
                }

            }
        });

       Handler handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){
                    String res=msg.obj.toString();
                    res=res.replace("[","'").replace("]","'");
                    try {
                        JSONObject jsonObject=new JSONObject(res);

                        username=change(jsonObject.getString("username"));
                        comments= change(jsonObject.getString("comments"));
                        time=change(jsonObject.getString("time"));
                        position=change(jsonObject.getString("position"));
                        imageid=change(jsonObject.getString("imageid"));
                        List<Integer> images=new ArrayList<Integer>();
                        for (String s : imageid) {
                            images.add(Integer.parseInt(s));
                        }
                        List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
                        for (int i = 0; i < username.length; i++) {
                            Map<String,Object> listItem=new HashMap<String, Object>();
                            listItem.put("username",username[i]);
                            listItem.put("comments",comments[i]);
                            listItem.put("time",time[i]);
                            listItem.put("position",position[i]);
                            listItem.put("imageid",images.get(i));
                            listItems.add(listItem);
                        }
                        SimpleAdapter simpleAdapter=new SimpleAdapter(Find.this,listItems,R.layout.simple_item,
                                new String[]{"username","comments","time","position","imageid"},
                                new int[]{R.id.name,R.id.word,R.id.time,R.id.position,R.id.imageid}
                        );
                        list.setAdapter(simpleAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        accessComment(handler);

        index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Find.this,com.test.work.MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Find.this,com.test.work.Mine.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void accessComment(Handler handler)
    {
        new Thread(()->{

            OkHttpClient okHttpClient=new OkHttpClient();
            String url = "http://47.107.68.210:8080/exam3-6sarchive/comment.jsp";
            Request request = new Request.Builder().url(url).build();  //④

            Call call = okHttpClient.newCall(request);
            try {
                Response response=call.execute();
                Message msg = new Message();
                msg.what = 0x123;
                msg.obj = response.body().string();
                handler.sendMessage(msg);
            }catch (Exception e){
                System.out.println(e);
            }
        }).start();
    }
    public String[] change(String s){
        List<String> list= Arrays.asList(s.split(", "));
        return (String[]) list.toArray();
    }
}
