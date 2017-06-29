package com.example.administrator.myoschina;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myoschina.adapter.MyVPAdapter;
import com.example.administrator.myoschina.bean.TabBean;
import com.example.administrator.myoschina.widget.BottomLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TestActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private  List<ImageView> images;//轮番的图片
    private List<ImageView> dots;//小圆点
    //当前页
    private int currentItem;
    //上一页
    private int oldPosition;
    //标题
    private String[] title=new String[]{
            "高手问答|人工智能在电商的作业"
            ,"源创会|上海南京站开始报名"
            ,"混程序员的江湖"
            ,"我为什么不在乎人工智能"
            ,"维护vscode的事情"
    };
    //轮番图id
    private int[] imagesId=new int[]{
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5
    };
    private TextView tvTitle;
    private ScheduledExecutorService scheduledExecutorService;//线程池 开启定时循环任务
    private MyVPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mViewPager= (ViewPager) findViewById(R.id.vp);
        images=new ArrayList<>();
        for (int i = 0; i < imagesId.length; i++) {
            //循环添加ImageView控件到集合当中
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(imagesId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//图片拉伸
            images.add(imageView);
        }
        dots=new ArrayList<>();
        dots.add((ImageView) findViewById(R.id.dot0));
        dots.add((ImageView) findViewById(R.id.dot1));
        dots.add((ImageView) findViewById(R.id.dot2));
        dots.add((ImageView) findViewById(R.id.dot3));
        dots.add((ImageView) findViewById(R.id.dot4));
        dots.get(0).setImageResource(R.drawable.dot_focus);
        tvTitle= (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title[0]);
        adapter=new MyVPAdapter();

        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(images.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(title[position]);//改成对应标题
                dots.get(position).setImageResource(R.drawable.dot_focus);
                dots.get(oldPosition).setImageResource(R.drawable.d_dot);
                oldPosition=position;
                currentItem=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态 变化
                Log.d("state","onPageScrollStateChanged");
                switch (state){
                    case 1:
                        //拖动 停止轮番
                        stop();
                        break;
                    case 2:
                        //松手 启动轮番
                        if (scheduledExecutorService==null){
                            run();
                        }
                        run();
                        break;
                }

            }
        });
//        BottomLayout bottomLayout= (BottomLayout) findViewById(R.id.bottomLayout);
//        List<TabBean> tabs=new ArrayList<>();
//        tabs.add(new TabBean("综合",R.mipmap.ic_nav_news_actived,R.mipmap.ic_nav_my_normal,1));
//        tabs.add(new TabBean("动弹",R.mipmap.ic_nav_tweet_actived,R.mipmap.ic_nav_tweet_normal,1));
//        tabs.add(new TabBean("",R.mipmap.ic_nav_add_actived,R.mipmap.ic_nav_add_normal,0));
//        tabs.add(new TabBean("发现",R.mipmap.ic_nav_discover_actived,R.mipmap.ic_nav_discover_normal,1));
//        tabs.add(new TabBean("我的",R.mipmap.ic_nav_my_pressed,R.mipmap.ic_nav_my_normal,1));

    }

    @Override
    protected void onStop() {
        super.onStop();
        //activity进入后台 停止轮番
        stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (scheduledExecutorService ==null) {
            run();
        }
    }

    public void run(){
        //线程池对象 用来开启循环任务 在子线程中执行
        scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(),2,2, TimeUnit.SECONDS);//延迟2秒执行 间隔2秒执行
    }
    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            //切页面（子线程 修改UI——用Handler）
            currentItem=(currentItem+1) % imagesId.length;
           // mViewPager.setCurrentItem(currentItem); //子线程不能直接处理主线程UI
            mHandler.sendEmptyMessage(0);
        }
    }
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mViewPager.setCurrentItem(currentItem);
        }
    };
    public void stop(){
        //停止轮番
        if (scheduledExecutorService !=null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService=null;
        }
    }
    private class MyVPAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            //要展示的页数
            return images.size();
        }
        //判断是否同一页
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
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
