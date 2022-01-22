package com.test.work;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Add extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        SharedPreferences preferences;
        preferences=getSharedPreferences("Register",MODE_PRIVATE);
        String username=preferences.getString("name","");
        String imageid=preferences.getInt("imageId",R.drawable.icon)+"";
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String nowTime=dateFormat.format(date);
        EditText comment=(EditText)findViewById(R.id.comment);
        EditText position=(EditText)findViewById(R.id.position);
        Button save=(Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comments=comment.getText().toString();
                String positions=position.getText().toString();
                if(comments.equals("")||positions.equals("")){
                    Toast.makeText(Add.this,
                            "请将信息输入完整", Toast.LENGTH_SHORT).show();
                }else {
                    commentInf(username,comments,nowTime,positions,imageid);
                    Intent intent=new Intent(Add.this,Find.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void commentInf(String name,String comments,String time,String position,String imageid){
        new Thread(()->{
            OkHttpClient okHttpClient=new OkHttpClient();
            String url = "http://47.107.68.210:8080/exam3-6sarchive/add_comment.jsp";

            Map<String,Object> parameters=new HashMap<String,Object>();
            parameters.put("username",name);
            parameters.put("comments",comments);
            parameters.put("time",time);
            parameters.put("position",position);
            parameters.put("imageid",imageid);

            StringBuffer sb = new StringBuffer();
            //设置表单参数
            for (String key: parameters.keySet()) {
                sb.append(key+"="+parameters.get(key)+"&");
            }
            MediaType FORM_CONTENT_TYPE
                    = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
            RequestBody body = RequestBody.create(FORM_CONTENT_TYPE,sb.toString());
//            FormBody body = new FormBody.Builder()
//                    .add("username", name)
//                    .add("comments", comments)
//                    .add("time",time)
//                    .add("position",position)
//                    .add("imageid",imageid)
//                    .build();  //③
            Request request = new Request.Builder().url(url)
                    .post(body)
                    .build();  //④
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback()  // ⑤
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                    Looper.prepare();
                    Toast.makeText(Add.this,
                            e.toString(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    String res=response.body().string();
                        Looper.prepare();
                        Toast.makeText(Add.this,
                                res.trim(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                }
            });
        }).start();
    }
}
