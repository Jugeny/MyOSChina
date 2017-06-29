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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.adapter.CompreNewsRVAdapter;
import com.example.administrator.myoschina.adapter.TweetRVAdapter;
import com.example.administrator.myoschina.bean.TweetListResponse;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.AliHeader;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.administrator.myoschina.utils.OSChinaApi.TWEET_LIST;

/**
 * Created by Administrator on 2017/5/9.
 */

public class NewTweetFragment extends Fragment {
    List<TweetListResponse.TweetlistBean> tweetList;
    TweetRVAdapter adapter;
    SpringView springView;
    int pageIndex=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_new_tweet,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.rv_newTweet);
        springView= (SpringView) view.findViewById(R.id.spring_tweet);
        tweetList=new ArrayList<>();
        adapter=new TweetRVAdapter(getContext(),tweetList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);

        springView.setHeader(new AliHeader(getContext()));
        springView.setFooter(new AliFooter(getContext()));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
                getData();
            }

            @Override
            public void onLoadmore() {
                Toast.makeText(getContext(), "加载更多", Toast.LENGTH_SHORT).show();
            }
        });
        getData();
    }

    private void getData() {
        OkGo.get(TWEET_LIST)
                .tag(this)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .params("user",-1)
                .params("pageSize",20)
                .params("page",1)
                .params("dataType","json")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess:");
                        Gson gson=new Gson();
                        TweetListResponse tweetListResponse=gson.fromJson(s,TweetListResponse.class);
                        tweetList.clear();
                        tweetList.addAll(tweetListResponse.getTweetlist());
                        adapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        springView.onFinishFreshAndLoad();
                        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
