package com.example.administrator.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.adapter.SecondVPAdapter;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.administrator.myoschina.utils.OSChinaApi.TWEET_LIST;

/**
 * Created by Administrator on 2017/5/6.
 */

public class TweetFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成 view
        View v = inflater.inflate(R.layout.fragment_tweet,container,false);
        return v ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_tweet);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_tweet);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new NewTweetFragment());
        fragmentList.add(new PostFragment());
        fragmentList.add(new NewsFragment());
        SecondVPAdapter adapter = new SecondVPAdapter(getFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}
