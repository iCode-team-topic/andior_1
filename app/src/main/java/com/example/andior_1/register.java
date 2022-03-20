package com.example.andior_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register_click(View view) {
        EditText userId_editText = findViewById(R.id.userId_editText);
        EditText userName_editText = findViewById(R.id.userName_editText);
        EditText password_editText = findViewById(R.id.password_editText);

        //获取用户输入的id、昵称和密码
        String userId = userId_editText.getText().toString();
        String userName = userName_editText.getText().toString();
        String password = password_editText.getText().toString();

//        byOkHttp(userId,userName,password);
        byOkHttpUtils(userId,userName,password);
    }
    public void byOkHttpUtils(String userId,String userName,String password){
        OkHttpUtils
                .post()
                .addParams("userId",userId)
                .addParams("userName",userName)
                .addParams("password",password)
                .url("http://192.168.43.222:8081/register")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        if(response.equals("true"))
                            Toast.makeText(register.this, "注册成功！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(register.this, "注册失败，可能已存在当前账号邮箱", Toast.LENGTH_LONG).show();
                    }
                });
    }
    public void byOkHttp(String userId,String userName,String password){
        //创建OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormEncodingBuilder()
                .add("userId", userId)
                .add("password", password)
                .build();

        // 建立请求并绑定数据
        Request request = new Request.Builder()
                .url("http://192.168.43.222:8081/register")
                .post(formBody)
                .build();

        //响应
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取返回的json数据
        String return_value = response.body().toString();
        if(return_value.equals("true")){
            Toast.makeText(register.this, "注册成功！", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(register.this, "注册失败，可能已存在当前账号邮箱", Toast.LENGTH_LONG).show();
        }
    }
}
