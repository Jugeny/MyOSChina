package com.example.administrator.myoschina.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myoschina.bean.TabBean;

/**
 * Created by Administrator on 2017/4/28.
 */

public class BottomTab extends LinearLayout {
    ImageView imageView;
    TextView textView;
    Context context;
    TabBean tabBean;
    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //标题 图片
    public  BottomTab(Context context, TabBean tab){
        super(context);
        this.context=context;
        this.tabBean=tab;
        init();
    }

    private void init() {
        //构建一个底部tab
        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(Gravity.CENTER);

        //添加图片
         imageView=new ImageView(context);
        imageView.setImageResource(tabBean.getUnSelect());
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LayoutParams ivParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1);

        linearLayout.addView(imageView,ivParams);
        if (tabBean.getType()==1) { // 1：图文 0：但图片
            //添加文字
            textView = new TextView(context);
            textView.setText(tabBean.getTitle());
            LayoutParams tvParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvParams.gravity = Gravity.CENTER;
            linearLayout.addView(textView, tvParams);
        }

            addView(linearLayout);

    }

    @Override
    public void setSelected(boolean selected) {
        if (selected){
            //textview和imageView改变
            textView.setTextColor(Color.GREEN);
            if (imageView!=null) {
                imageView.setImageResource(tabBean.getSelected());
            }
        }else {
            if (textView!=null) {
                textView.setTextColor(Color.BLACK);
            }
            if (imageView !=null){
                imageView.setImageResource(tabBean.getUnSelect());
            }
        }
    }
}
