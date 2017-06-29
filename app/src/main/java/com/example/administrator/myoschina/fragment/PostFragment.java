package com.example.administrator.myoschina.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.adapter.PostRVAdapter;
import com.example.administrator.myoschina.bean.PostResponse;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/5/3.
 */

public class PostFragment extends Fragment {
    List<PostResponse.PostListBean> postList;
    private PostRVAdapter adapter;
    int pageIndex=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_post,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpringView springView= (SpringView) view.findViewById(R.id.spring_post);
        springView.setHeader(new DefaultHeader(getContext()));//设置头布局
        springView.setHeader(new DefaultFooter(getContext()));//设置尾布局
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新数据
                postList.clear();//清空集合
                pageIndex=1;
                getData();
            }

            @Override
            public void onLoadmore() {
                //上拉加载更多
                pageIndex++;
                getData();

            }
        });
        //处理控件
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.rv_post);
        postList=new ArrayList<>();
        adapter=new PostRVAdapter(getContext(),postList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        //获取数据
        getData();
    }

    private void getData() {
//        String access_token=getContext().getSharedPreferences("oschina",MODE_PRIVATE)
//                .getString("access_token","");
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.POST_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("catalog",1)
                .params("page",pageIndex)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG,"onSuccess:");
                        Gson gson=new GsonBuilder().registerTypeHierarchyAdapter(PostResponse.PostListBean.AnswerBean.class,
                                new JsonDeserializer<PostResponse.PostListBean.AnswerBean>() {
                                    @Override
                                    public PostResponse.PostListBean.AnswerBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                                            throws JsonParseException {
                                        Log.d(TAG,"deserialize:");
                                        Gson newGson=new Gson();
                                        PostResponse.PostListBean.AnswerBean answer;
                                        try {
                                            answer = newGson.fromJson(json, PostResponse.PostListBean.AnswerBean.class);
                                        }catch (Exception e) {
                                            answer=new PostResponse.PostListBean.AnswerBean();
                                            answer.setName("");
                                            answer.setTime("");
                                        }
                                        return answer;
                                    }
                                }).create();
                        PostResponse postResponse=gson.fromJson(s,PostResponse.class);
                        postList.addAll(postResponse.getPost_list());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.d(TAG,"onError:");
                    }
                });
    }
}
