package com.example.administrator.myoschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myoschina.NewsDetailActivity;
import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.bean.NewsListResponse;

import java.util.List;

/**
 * Created by Administrator on 2017/4/28.
 */

public class CompreNewsRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_HEAD=0;
    private int TYPE_NORMAL=1;
    //1.数据源  2.item布局
    Context context;
    List<NewsListResponse.NewslistBean> newslist;

    public CompreNewsRVAdapter(Context context, List<NewsListResponse.NewslistBean> newslist) {
        this.context=context;
        this.newslist=newslist;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==TYPE_HEAD){
            //加载 轮番图（头布局）
            HeadViewHolder headViewHolder=new HeadViewHolder(mHeadView);
            return headViewHolder;
        }else{
            //加载 新闻item
            View view= LayoutInflater.from(context).inflate(R.layout.item_news,parent,false);
            NewsViewHolder vh=new NewsViewHolder(view);

            return vh;
        }
        //创建viewHolder

    }
    private View mHeadView;
    //设置头布局
    public void setHead(View view){
        mHeadView=view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEAD){

        }else {
            //绑定数据
            final NewsListResponse.NewslistBean news=newslist.get(position-1);
            NewsViewHolder newsViewHolder= (NewsViewHolder) holder;
            String pubTime=news.getPubDate();//转换成X小时前
            newsViewHolder.title.setText(news.getTitle());
            newsViewHolder.comment.setText(news.getCommentCount()+"");
            newsViewHolder.time.setText(pubTime);
            if (pubTime.equals("昨天")){//判断是今天以前的
                newsViewHolder.tag.setVisibility(View.GONE);
            }
            newsViewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转到对应的详情页
                    Toast.makeText(context,"点击了"+news.getId(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("newsid",news.getId());
                    context.startActivity(intent);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        int size=newslist.size();
        if (mHeadView!=null){
            size++;
        }
        return size;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView tag;
        TextView title;
        TextView content;
        TextView time;
        TextView comment;
        LinearLayout item;
        public NewsViewHolder(View itemView) {
            super(itemView);
            tag= (ImageView) itemView.findViewWithTag(R.id.image_item_tag);
            title= (TextView) itemView.findViewById(R.id.tv_item_title);
//            content=itemView.findViewById(R.id.);
            time= (TextView) itemView.findViewById(R.id.tv_item_time);
            comment= (TextView) itemView.findViewById(R.id.tv_comment_count);
            item= (LinearLayout) itemView;
        }
    }
    public class HeadViewHolder extends RecyclerView.ViewHolder{

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //获取item的类型
        if (position==0 & mHeadView!=null){
            return TYPE_HEAD;
        }else {
            return TYPE_NORMAL;
        }
    }
}
