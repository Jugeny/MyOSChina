package com.example.administrator.myoschina;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.bean.NewsListResponse;
import com.example.administrator.myoschina.bean.TabBean;
import com.example.administrator.myoschina.fragment.ComprehensiveFragment;
import com.example.administrator.myoschina.fragment.DiscoverFragment;
import com.example.administrator.myoschina.fragment.MyFragment;
import com.example.administrator.myoschina.fragment.NewsFragment;
import com.example.administrator.myoschina.fragment.TweetFragment;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.widget.BottomLayout;
import com.example.administrator.myoschina.widget.UnScrollViewPager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    UnScrollViewPager viewPager;
    MyVPAdapter madapter;
    List<Fragment> fragmentList = new ArrayList<>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("综合");
        //添加四大模块fragment
        fragmentList.add(new ComprehensiveFragment());
        fragmentList.add(new TweetFragment());
        fragmentList.add(new DiscoverFragment());
        fragmentList.add(new MyFragment());

        madapter = new MyVPAdapter(getSupportFragmentManager(), fragmentList);

        viewPager = (UnScrollViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(madapter);
        viewPager.setOffscreenPageLimit(4);
        BottomLayout bottomLayout = (BottomLayout) findViewById(R.id.bottomLayout);
        List<TabBean> tabs = new ArrayList<>();
        tabs.add(new TabBean("综合", R.mipmap.ic_nav_news_actived, R.mipmap.ic_nav_news_normal, 1));
        tabs.add(new TabBean("动弹", R.mipmap.ic_nav_tweet_actived, R.mipmap.ic_nav_tweet_normal, 1));
        tabs.add(new TabBean("", R.mipmap.ic_nav_add_actived, R.mipmap.ic_nav_add_normal, 0));
        tabs.add(new TabBean("发现", R.mipmap.ic_nav_discover_actived, R.mipmap.ic_nav_discover_normal, 1));
        tabs.add(new TabBean("我的", R.mipmap.ic_nav_my_pressed, R.mipmap.ic_nav_my_normal, 1));

        bottomLayout.setMidClickListen(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了中间", Toast.LENGTH_SHORT).show();
            }
        });//中间按钮点击监听回调方法
        bottomLayout.setBottom(this, tabs,viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setTitle("综合");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setTitle("动弹");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setTitle("发现");
                        toolbar.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        toolbar.setVisibility(View.GONE);//隐藏标题
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }
}

