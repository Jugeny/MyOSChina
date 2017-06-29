package com.example.administrator.myoschina.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.TestActivity;
import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.bean.ScrollImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/4.
 */

public class ScrollImageLayout extends LinearLayout {
    private ViewPager mViewPager;
    private List<ImageView> images;//轮番的图片
    private List<ImageView> dots;//小圆点
    //当前页
    private int currentItem;
    //上一页
    private int oldPosition=0;
    private TextView tvTitle;
    private ScheduledExecutorService scheduledExecutorService;//线程池 开启定时循环任务
    private MyVPAdapter adapter;
    Context context;
    List<ScrollImageBean> imageList;
    private String[] titles;
    private int[] imageId;


    public ScrollImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setImages(Context context, List<ScrollImageBean> imageList) {
        this.context = context;
        this.imageList = imageList;
        init();
    }

    private void init() {
        //初始化 控件以及数据
        View view = LayoutInflater.from(context).inflate(R.layout.layout_scrollimage, this, false);
        addView(view);
        LinearLayout ll_dot = (LinearLayout) view.findViewById(R.id.ll_dots);
        //标题
        titles = new String[imageList.size()];
        //轮番图片id
        imageId = new int[imageList.size()];
        dots = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {
            titles[i] = imageList.get(i).getTitle();
            imageId[i] = imageList.get(i).getImageId();
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.d_dot);
            LayoutParams params = new LayoutParams(40, 40);
            dots.add(imageView);//小圆点加入集合 方便一会管理(变色)
            ll_dot.addView(imageView, params);
        }
        mViewPager = (ViewPager) findViewById(R.id.vp);
        images = new ArrayList<>();
        for (int i = 0; i < imageId.length; i++) {
            //循环添加ImageView控件到集合当中
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//图片拉伸
            images.add(imageView);

        }
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(titles[0]);
        adapter = new MyVPAdapter();
        mViewPager.setAdapter(adapter);
        dots.get(0).setImageResource(R.drawable.dot_focus);
        mViewPager.setOffscreenPageLimit(images.size());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(titles[position]);//改成对应标题
                dots.get(position).setImageResource(R.drawable.dot_focus);
                dots.get(oldPosition).setImageResource(R.drawable.d_dot);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态 变化
                Log.d("state", "onPageScrollStateChanged:"+state);
                switch (state) {
                    case 1:
                        //拖动 停止轮番
                        stop();
                        break;
                    case 2:
                        //松手 启动轮番
                        if (scheduledExecutorService == null) {
                            run();
                        }
                        break;
                }

            }
        });
    }


    public void run() {
        //线程池对象 用来开启循环任务 在子线程中执行
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 2, 2, TimeUnit.SECONDS);//延迟2秒执行 间隔2秒执行
    }

    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            //切页面（子线程 修改UI——用Handler）
            currentItem = (currentItem + 1) % imageId.length;
            // mViewPager.setCurrentItem(currentItem); //子线程不能直接处理主线程UI
            mHandler.sendEmptyMessage(0);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(currentItem);
        }
    };

    public void stop() {
        //停止轮番
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }

    private class MyVPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            //要展示的页数
            return images.size();
        }

        //判断是否同一页
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //如果滑动图片超出缓存 会调用这个方法 将图片销毁（默认缓存3页）
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(images.get(position));
        }

        //当要显示的图片可以进行缓存的时候 会调用这个方法进行图片初始化
        //将要展示的imagView加入到ViewGroup当中 然后作为返回值即可
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(images.get(position));
            return images.get(position);
        }
    }
}
