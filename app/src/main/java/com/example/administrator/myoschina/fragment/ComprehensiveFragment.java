package com.example.administrator.myoschina.fragment;

import android.content.Context;
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
import android.widget.LinearLayout;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.adapter.CompreNewsRVAdapter;
import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.bean.NewsListResponse;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ComprehensiveFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //根据对应的布局文件 生成view
        View v=inflater.inflate(R.layout.fragment_conprehensive,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //找控件
        TabLayout tabLayout= (TabLayout) view.findViewById(R.id.tabLayout_compre);
        ViewPager vp= (ViewPager) view.findViewById(R.id.vp_compre);

        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new NewsFragment());
        fragmentList.add(new NewsFragment());
        fragmentList.add(new PostFragment());
        fragmentList.add(new NewsFragment());
        //给ViewPager设置adapter
        MyVPAdapter adapter=new MyVPAdapter(getFragmentManager(),fragmentList);
        vp.setAdapter(adapter);
        //tabLayout与viewPager关联
        tabLayout.setupWithViewPager(vp);

    }
}
