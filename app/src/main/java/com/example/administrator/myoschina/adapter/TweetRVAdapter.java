package com.example.administrator.myoschina.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.TweetDetailActivity;
import com.example.administrator.myoschina.bean.TweetListResponse;
import com.example.administrator.myoschina.widget.ImageLoad;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */

public class TweetRVAdapter extends RecyclerView.Adapter<TweetRVAdapter.ViewHolder> {
    public static final int TYPE_HEADER = 0;//头
    public static final int TYPE_NORMAL = 1;//普通
    Context context;
    List<TweetListResponse.TweetlistBean> tweetlist;
    public TweetRVAdapter(Context context, List<TweetListResponse.TweetlistBean> tweetlist) {
        this.context = context;
        this.tweetlist = tweetlist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(TweetRVAdapter.ViewHolder holder, int position) {
        //绑定数据
            final TweetListResponse.TweetlistBean tweet = tweetlist.get(position);
            holder.head.setImageURI(tweet.getPortrait());
            holder.name.setText(tweet.getAuthor());
            holder.content.setText(tweet.getBody());
            holder.time.setText(tweet.getPubDate());
            holder.comment.setText(tweet.getCommentCount() + "");
            String image = tweet.getImgSmall();
            //清空子view
            for (int i = 0; i < holder.imageLoad.getChildCount(); i++) {
                holder.imageLoad.removeView(holder.imageLoad.getChildAt(i));
            }
            //加载图片
            if (image == null) {
                holder.imageLoad.setVisibility(View.GONE);
            } else {
                holder.imageLoad.setVisibility(View.VISIBLE);
                holder.imageLoad.setImages(image);

            }
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TweetDetailActivity.class);
                    intent.putExtra("tweetId", tweet.getId());
                    context.startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return tweetlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            SimpleDraweeView head;
            TextView name;
            TextView content;
            TextView time;
            TextView comment;
            ImageLoad imageLoad;
            LinearLayout item;
            public ViewHolder(View itemView) {
                super(itemView);
                item = (LinearLayout) itemView;
                head = (SimpleDraweeView) itemView.findViewById(R.id.image_tweet_head);
                name = (TextView) itemView.findViewById(R.id.tv_tweet_name);
                content = (TextView) itemView.findViewById(R.id.tv_tweet_content);
                time    = (TextView) itemView.findViewById(R.id.tv_tweet_time);
                comment = (TextView) itemView.findViewById(R.id.tv_tweet_comment);
                imageLoad = (ImageLoad) itemView.findViewById(R.id.imageLoad);
            }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_HEADER;
        }else {
            return TYPE_NORMAL;
        }
    }
}
