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
import com.example.administrator.myoschina.adapter.TweetCommentAdapter;
import com.example.administrator.myoschina.adapter.TweetRVAdapter;
import com.example.administrator.myoschina.bean.TweetCommentResponse;
import com.example.administrator.myoschina.bean.TweetListResponse;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/9.
 */

public class TweetCommentFragment extends Fragment {
    List<TweetCommentResponse.CommentListBean> tCommentList;
    private TweetCommentAdapter adapter;
    int id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tweet_comment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.rv_tweet_comment);
        tCommentList=new ArrayList<>();
        adapter=new TweetCommentAdapter(getContext(),tCommentList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        id=getActivity().getIntent().getIntExtra("tweetId",0);
        String access_token= PreferencesUtils.getString("access_token");
        OkGo.get(OSChinaApi.COMMENT_LIST)
                .tag(this)
                .params("access_token",access_token)
                .params("id",id )
                .params("catalog",3)
                .params("page",2)
                .params("pageSize",20)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("s","onSuccess:");
                        Gson gson=new Gson();
                        TweetCommentResponse tweetCommentResponse=gson.fromJson(s, TweetCommentResponse.class);
                        List<TweetCommentResponse.CommentListBean> tComment=tweetCommentResponse.getCommentList();
                        tCommentList.addAll(tComment);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
