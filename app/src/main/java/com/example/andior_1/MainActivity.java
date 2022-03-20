package com.example.andior_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //注册按钮事件，跳转到注册界面
    public void register_onClick(View view) {
        Intent intent = new Intent(MainActivity.this, register.class);
        startActivity(intent);
    }

    //登录按钮事件，进行登录
    public void login_onClick(View view) {
        final EditText userId_editText = findViewById(R.id.userId_editText);
        EditText password_editText = findViewById(R.id.password_editText);

        String userId = userId_editText.getText().toString();
        String password = password_editText.getText().toString();

        OkHttpUtils
                .post()
                .url("http://192.168.43.222:8081/login")
                .addParams("userId", userId)
                .addParams("password",password)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Toast.makeText(MainActivity.this, "测试失误", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("true"))
                            Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                    }
                });
//        OkHttpUtils.get().url("http://10.161.62.240:8080/getUsers").build().execute(new StringCallback() {
//            @Override
//            public void onError(Request request, Exception e) {
//            }
//
//            @Override
//            public void onResponse(String response) {
//                System.out.println("======"+response);
//                EditText editText = findViewById(R.id.userId_editText);
//                editText.setText(response);
//            }
//        });
    }


//    //点击登录触发事件
//    public void login_click(View view) {
//        //获取编辑框的内容 id和密码
//        String id = ((EditText) findViewById(R.id.myIdEditText)).getText().toString();
//
//        String password = ((EditText) findViewById(R.id.myPasswordEditText)).getText().toString();
//
//        OkHttpUtils
//                .post()
//                .url("http://10.60.99.245:8080/register")
//                .addParams("userId",id)
//                .addParams("password",password)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Request request, Exception e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response) {
//                        String str=response;
//                        if(str.equals("true")){
//                            str="注册成功";
//                        }
//                    }
//                });


//        System.out.println("1============");
//        //创建OkHttpClient
//        OkHttpClient okHttpClient = new OkHttpClient();
//        System.out.println("2============");
//
//        //获取编辑框的内容 id
//        String id = ((EditText) findViewById(R.id.myIdEditText)).getText().toString();
//
//        String password = ((EditText) findViewById(R.id.myPasswordEditText)).getText().toString();
//
//
//        System.out.println("3============");
//        //建立请求表单，添加上传服务器的参数
//        RequestBody formBody = new FormEncodingBuilder()
//                .add("userId", id)
//                .add("password", password)
//                .build();
//        System.out.println("4============");
//        // 建立请求并绑定数据
//        Request request = new Request.Builder()
//                .url("http://10.60.99.245:8080/register")
//                .post(formBody)
//                .build();
//        System.out.println("5============");
//        //响应
//        Response response = null;
//        try {
//            response = okHttpClient.newCall(request).execute();
//            System.out.println(response+"====");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("6============");
//        //获取返回的json数据
//        String return_value = response.body().toString();
//        String str="";
//        if(return_value.equals("true")){
//            str="注册成功";
//        }
//        else {
//            str="注册失败，可能已存在当前账号";
//        }
//        System.out.println("7============");
//        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
//        System.out.println("8============");


}
