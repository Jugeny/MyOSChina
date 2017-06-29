package com.example.administrator.myoschina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.administrator.myoschina.bean.MessageEvent;
import com.example.administrator.myoschina.bean.NewsDetailResponse;
import com.example.administrator.myoschina.utils.OSChinaApi;
import com.example.administrator.myoschina.utils.PreferencesUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import okhttp3.Call;
import okhttp3.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView webView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        webView = (WebView) findViewById(R.id.webView_newsdetail);
        btn= (Button) findViewById(R.id.btnFav);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过eventBus发送一条消息
                EventBus.getDefault().post(new MessageEvent("收藏成功"));
            }
        });
        //配置webView
        WebSettings webSettings= webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient());//在本应用中跳转

        int id=getIntent().getIntExtra("newsid",0);
        getData(id);
    }
    private void getData(int id){
        OkGo.get(OSChinaApi.NEWS_DETAIL)
                .tag(this)
                .params("id",id)
                .params("access_token", PreferencesUtils.getString("access_token"))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d("d","onSuccess:"+s);
                        Gson gson=new Gson();
                        NewsDetailResponse newsDetailResponse=gson.fromJson(s,NewsDetailResponse.class);
                        String url=newsDetailResponse.getUrl();
                        webView.loadUrl(url);
                    }
                });
    }

}
