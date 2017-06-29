package com.example.administrator.myoschina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2017/5/14.
 */

public class MyFragment extends Fragment {

    private TextView tvColl;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvColl = (TextView) view.findViewById(R.id.tvColl);
        imageView = (ImageView) view.findViewById(R.id.iv_redpoint);
        tvColl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
            }
        });
    }
    @Subscribe
    public void  handleMessage(MessageEvent event){
        //接收到消息后的处理事件
        Toast.makeText(getContext(), "收藏成功", Toast.LENGTH_SHORT).show();
        int a=Integer.parseInt( tvColl.getText().toString());
        a++;
        tvColl.setText( a +"");
        imageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册eventbus 接收消息
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
