package com.example.administrator.myoschina;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * 星轨自定义View
 * Created by Administrator on 2017/5/12.
 */

public class MyView extends View {
    Context context;
    int radiams[]=new int[]{90,90,90,90,90};
    int radiam=90;//初始角度
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        timeTread.start();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
    }
    //每隔一定时间增加角度，刷新view，实心圆运动
    private Thread timeTread=new Thread() {
        @Override
        public void run() {
            try {
                while (true){
                    Thread.sleep(150);
//                    radiam++;
                    for (int i = 0; i <5 ; i++) {
                        if (i==2||i==3){
                            radiams[i] -=i*2+1;
                        }else {
                            radiams[i] += i * 2 + 1;
                        }
                    }
                    updateHandler.sendEmptyMessage(0);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler updateHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //刷新
            invalidate();
        }
    };
    /**
     * 用来绘制自定义view
     * @param canvas 画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int r=120;
        //空心圆
        for (int i = 0; i < 5; i++) {

            Paint paint=new Paint();//画笔
            paint.setStyle(Paint.Style.STROKE);//空心
            paint.setColor(getResources().getColor(R.color.colorWhite));
            paint.setStrokeWidth(1f);//粗细
            paint.setAntiAlias(true);//抗锯齿
            //画空心圆
            canvas.drawCircle(getWidth()/2,getHeight()/2,r,paint);
            //实心圆
            Paint paintPoint=new Paint();
            paintPoint.setAntiAlias(true);
            paintPoint.setColor(getResources().getColor(R.color.colorWhite));
//            r=getWidth()/2;
            int x= (int) (getWidth()/2 + r * Math.sin(Math.PI* radiams[i]/180));
            int y= (int) (getHeight()/2 + r * Math.cos(Math.PI* radiams[i]/180));
            canvas.drawCircle(x,y,8,paintPoint);
            r+=100+i*30;
        }



    }
    //用来测量该自定义view的大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(myMeasure(widthMeasureSpec),myMeasure(heightMeasureSpec));

    }
    //自定义测量模式
    private int myMeasure(int origin) {
        int result=300;//默认宽高
        //测量宽或高
        //获取测量模式
        int specMode=MeasureSpec.getMode(origin);
        //获取具体值
        int specSize=MeasureSpec.getSize(origin);
        if (specMode==MeasureSpec.EXACTLY){
            result=specSize;
        }else if (specMode==MeasureSpec.AT_MOST){
            result=Math.min(result,specSize);
        }
        return result;
    }
}
