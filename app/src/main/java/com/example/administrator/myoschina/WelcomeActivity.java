package com.example.administrator.myoschina;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome();
    }

    private void welcome() {
        //判断是否登陆过 3s后执行对应跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String access_token=getSharedPreferences("oschina",MODE_PRIVATE)
                        .getString("access_token",null);
                if (access_token==null){
                    //为空则跳转认证界面
                    Intent intent=new Intent(WelcomeActivity.this,AuthorActivity.class);
                    startActivity(intent);
                }else {
                    //不为空则跳转主界面
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);

                }
               finish();
            }
        }, 3000);
    }
}
