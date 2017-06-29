package com.example.administrator.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.adapter.CompreNewsRVAdapter;
import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.bean.NewsListResponse;
import com.example.administrator.myoschina.bean.ScrollImageBean;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.widget.ScrollImageLayout;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/5/2.
 */

public class NewsFragment extends Fragment {
    List<NewsListResponse.NewslistBean> newsList;
    CompreNewsRVAdapter adapter;
    private ScrollImageLayout scrollImageLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成view
        View v=inflater.inflate(R.layout.fragment_news,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //构建新闻列表

        newsList=new ArrayList<>();
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_compre_news);
        adapter=new CompreNewsRVAdapter(getContext(),newsList);


        scrollImageLayout=new ScrollImageLayout(getContext(),null);
        List<ScrollImageBean> images=new ArrayList<>();
        images.add(new ScrollImageBean("高手问答|人工智能在电商的作业",R.drawable.a1));
        images.add(new ScrollImageBean("源创会|上海南京站开始报名",R.drawable.a2));
        images.add(new ScrollImageBean("混程序员的江湖",R.drawable.a3));
        images.add(new ScrollImageBean("我为什么不在乎人工智能",R.drawable.a4));
        images.add(new ScrollImageBean("维护vscode的事情",R.drawable.a5));
        scrollImageLayout.setImages(getContext(),images);
        adapter.setHead(scrollImageLayout);


        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);

        getData();

    }
    @Override
    public void onStart() {
        super.onStart();
        scrollImageLayout.run();
    }

    @Override
    public void onStop() {
        super.onStop();
        scrollImageLayout.stop();
    }

    private void getData() {
        String token=getContext().getSharedPreferences("oschina",MODE_PRIVATE).getString("access_token","");
        OkGo.get(OSChinaApi.NEWS_LIST)
                .tag(this)
                .params("access_token",token)
                .params("catalog",1)
                .params("page",1)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG,"onSuccess:");
                        Gson gson=new Gson();
                        NewsListResponse newsListresponse=gson.fromJson(s,NewsListResponse.class);
                        List<NewsListResponse.NewslistBean> data=newsListresponse.getNewslist();
                        newsList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

}
