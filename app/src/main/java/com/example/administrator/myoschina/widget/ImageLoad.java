package com.example.administrator.myoschina.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2017/5/6.
 */

public class ImageLoad extends LinearLayout {
    Context context;
    String[] images;
    public ImageLoad(Context context) {
        this(context,null);

    }

    public ImageLoad(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public ImageLoad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }
    public void setImages(String images){
        this.images=images.split(",");
        init();
    }

    private void init() {
        //加载图片
        setOrientation(VERTICAL);
        int count=images.length;
        int lines;
        if (count%3==0) {
            lines = count / 3;
        }else {
            lines=count/3+1;
        }
        for (int i = 0; i < lines; i++) {
            //加1行
            LinearLayout linearLayout=new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            for (int j = i*3; j <(i+1)*3; j++) {
                //循环添加每行的图片
                SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
                String url;
                try {
                    url=images[j];
                    if (j>=1){
                        url="https://static.oschina.net/uploads/space/"+url;
                    }
                }catch (Exception e){
                    url="";
                }

                simpleDraweeView.setImageURI(url);
                LayoutParams params=new LayoutParams(0,360,1);
                linearLayout.addView(simpleDraweeView,params);
            }
            addView(linearLayout);//将每一行添加到本布局
        }
    }
}
