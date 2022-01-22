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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);Button login_btn=(Button)findViewById(R.id.login_btn);
        Button register_btn=(Button)findViewById(R.id.register_btn);
        EditText password= (EditText) findViewById(R.id.login_password);
        EditText account=(EditText)findViewById(R.id.email);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Login.this,Register.class);
                startActivityForResult(intent,0);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(password.getText().toString().equals("")||account.getText().toString().equals("")){
                    Toast.makeText(Login.this, "请将登录信息输入完整！", Toast.LENGTH_LONG).show();
                }else {
                    loginInf(account.getText().toString(),password.getText().toString());
                    Intent intent=new Intent(Login.this,Mine.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0&&resultCode==0){
            if(data!=null){
                Bundle bundle=data.getExtras();
                String email=bundle.getString("email");
                String pwd=bundle.getString("pwd");
                EditText password= (EditText) findViewById(R.id.login_password);
                EditText account=(EditText)findViewById(R.id.email);
                password.setText(pwd);
                account.setText(email);
            }
        }
    }
    public void loginInf(String email,String pwd){
        new Thread(()->{
            OkHttpClient okHttpClient=new OkHttpClient();
            String url = "http://47.107.68.210:8080/exam3-6sarchive/logintest.jsp";
            FormBody body = new FormBody.Builder()
                    .add("email", email)
                    .add("pwd", pwd)
                    .build();  //③
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
                    String res=response.body().string();
                    if(res.trim().equals("密码不正确")||res.trim().equals("账号尚未注册")){
                        Looper.prepare();
                        Toast.makeText(Login.this,
                                res.trim(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }else{
                        try {
                            JSONObject jsonObject=new JSONObject(res);
                            SharedPreferences preferences;
                            SharedPreferences.Editor editor;
                            preferences=getSharedPreferences("Register",MODE_PRIVATE);
                            editor=preferences.edit();
                            editor.putString("email",email);
                            editor.putString("pwd",pwd);
                            editor.putInt("imageId",Integer.parseInt(jsonObject.getString("imageId").toString()));
                            editor.putString("name",jsonObject.getString("name").toString());
                            editor.putString("userword",jsonObject.getString("userword").toString());
                            editor.putInt("status",1);
                            editor.apply();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Looper.prepare();
                        Toast.makeText(Login.this,
                                "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Login.this,Mine.class);
                        startActivity(intent);
                        Looper.loop();
                    }
                }
            });
        }).start();
    }
}