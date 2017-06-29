package com.example.administrator.myoschina;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.myoschina.adapter.TweetVPAdapter;
import com.example.administrator.myoschina.bean.TweetDetailResponse;
import com.example.administrator.myoschina.fragment.PostFragment;
import com.example.administrator.myoschina.fragment.TweetCommentFragment;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class TweetDetailActivity extends AppCompatActivity {
    private SimpleDraweeView imageHead;
    private TextView tvName;
    private TextView tvBody;
    private  TextView tvTime;
    private int id;
    TabLayout tabLayout;
    ViewPager viewPager;
    TweetVPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);
        imageHead= (SimpleDraweeView) findViewById(R.id.image_head);
        tvName= (TextView) findViewById(R.id.tv_name);
        tvBody= (TextView) findViewById(R.id.tv_content);
        tvTime= (TextView) findViewById(R.id.tv_time);
        tabLayout= (TabLayout) findViewById(R.id.tab_tweet_detail);
        viewPager= (ViewPager) findViewById(R.id.vp_tweetdetail);
        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new PostFragment());
        fragmentList.add(new TweetCommentFragment());
        adapter=new TweetVPAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        id = getIntent().getIntExtra("tweetId",0);
        getData();
    }

    public void getData() {
        OkGo.get(OSChinaApi.TWEET_DETAIL)
                .tag(this)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("id",id)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("d","OnSuccess:");
                        Gson gson=new Gson();
                        TweetDetailResponse tweetDetailResponse=gson.fromJson(s,TweetDetailResponse.class);
                        imageHead.setImageURI(tweetDetailResponse.getPortrait());
                        tvName.setText(tweetDetailResponse.getAuthor());
                        tvBody.setText(tweetDetailResponse.getBody());
                        tvTime.setText(tweetDetailResponse.getPubDate()+"");
                    }
                });

    }
}
