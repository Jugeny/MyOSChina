package com.example.administrator.myoschina;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class Test3Activity extends AppCompatActivity {
    SensorManager sensorManager;//传感器管理器，负责注册相关传感器，监听对应的动作
    private Vibrator vibrator;
    boolean isStart=false;
    ImageView wavepic1,wavepic2;
    private Animation mAnimation1,mAnimation2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        wavepic1= (ImageView) findViewById(R.id.wavepic1);
        wavepic2= (ImageView) findViewById(R.id.wavepic2);
        vibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        //通过sensorManager注册相关传感器
        //1.传感器监听 2.传感类型（加速度传感器）  3.接收传感器信息的频率
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values=event.values;
            float x=values[0];//x轴方向的加速度值
            float y=values[1];
            float z=values[2];
            int medumValue=19;
            if (Math.abs(x)>medumValue||Math.abs( y)>medumValue ||Math.abs(z)>medumValue){
                vibrator.vibrate(200);

                if (!isStart){
                    shake();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void shake() {
        mAnimation1= AnimationUtils.loadAnimation(this,R.anim.translate_1);
        mAnimation2=AnimationUtils.loadAnimation(this,R.anim.translate_2);
        wavepic1.startAnimation(mAnimation1);
        wavepic2.startAnimation(mAnimation2);
        isStart=true;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isStart=false;
                Toast.makeText(Test3Activity.this, "摇了摇", Toast.LENGTH_SHORT).show();
            }
        },2000);
    }
}
