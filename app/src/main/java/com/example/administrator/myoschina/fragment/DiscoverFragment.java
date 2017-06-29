package com.example.administrator.myoschina.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.Test3Activity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by Administrator on 2017/5/12.
 */

public class DiscoverFragment extends Fragment {
    TextView name1,name2,name3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discover,container,false);
        return v ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name1= (TextView) view.findViewById(R.id.name1);
        name2= (TextView) view.findViewById(R.id.name2);
        name3= (TextView) view.findViewById(R.id.name3);
        name2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Test3Activity.class);
                startActivity(intent);
            }
        });
        name3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (data!=null){
                    Bundle bundle=data.getExtras();
                    if (bundle==null){
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS){
                        //成功
                        String result=bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getContext(), "解析结果"+result, Toast.LENGTH_SHORT).show();
                    }else {
                        //失败
                        Toast.makeText(getContext(), "解析失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
