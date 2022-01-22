package com.test.work;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
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

public class Register extends Activity {
    int imageId=0;
    private EditText account;
    private EditText password;
    private EditText name;
    private EditText word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Button reg_btn=(Button)findViewById(R.id.reg_btn);
        account = (EditText) findViewById(R.id.account);
        password = (EditText) findViewById(R.id.password);
        name=(EditText)findViewById(R.id.name);
        word=(EditText)findViewById(R.id.word);
        ImageView imageView=(ImageView)findViewById(R.id.icon);
        imageId=R.drawable.tubiao9;
        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=account.getText().toString();
                String pwd=password.getText().toString();
                String username=name.getText().toString();
                String userword=word.getText().toString();

                if(email.equals("")&&pwd.equals("")){
                    Toast.makeText(Register.this, "请将注册信息输入完整！", Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences preferences;
                    SharedPreferences.Editor editor;
                    preferences=getSharedPreferences("Register",MODE_PRIVATE);
                    editor=preferences.edit();
                    editor.putString("email",email);
                    editor.putString("pwd",pwd);
                    editor.putInt("imageId",imageId);
                    editor.putString("name",username);
                    editor.putString("userword",userword);
                    editor.putInt("status",1);
                    editor.commit();

                    register(email,pwd,username,userword,imageId);
                    Intent intent=getIntent();
                    Bundle bundle=new Bundle();
                    bundle.putCharSequence("email",email);
                    bundle.putCharSequence("pwd",pwd);
                    intent.putExtras(bundle);
                    setResult(0,intent);
                    finish();
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,SelectIconActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1&&resultCode==1){
            Bundle bundle=data.getExtras();
            ImageView imageView=(ImageView)findViewById(R.id.icon);
            imageId=bundle.getInt("imageId");
            imageView.setImageResource(imageId);
        }
    }

    public void register(String email,String pwd,String username,String userword,int imageId){
        new Thread(()->{
            OkHttpClient okHttpClient=new OkHttpClient();
            String url = "http://47.107.68.210:8080/exam3-6sarchive/register.jsp";

            Map<String,Object> parameters=new HashMap<String,Object>();
            parameters.put("email",email);
            parameters.put("pwd",pwd);
            parameters.put("username",username);
            parameters.put("userword",userword);
            parameters.put("imageId",imageId);

            StringBuffer sb = new StringBuffer();
            //设置表单参数
            for (String key: parameters.keySet()) {
                sb.append(key+"="+parameters.get(key)+"&");
            }
            MediaType FORM_CONTENT_TYPE
                    = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
            RequestBody body = RequestBody.create(FORM_CONTENT_TYPE,sb.toString());

//            FormBody body = new FormBody.Builder()
//                    .add("email", email)
//                    .add("pwd", pwd)
//                    .add("username",username)
//                    .add("userword",userword)
//                    .add("imageId",""+imageId)
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
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    Looper.prepare();
                    Toast.makeText(Register.this,
                            response.body().string().trim(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            });
        }).start();
    }

}

