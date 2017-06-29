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
import com.example.administrator.myoschina.bean.TweetCommentResponse;
import com.example.administrator.myoschina.bean.TweetListResponse;
import com.example.administrator.myoschina.fragment.TweetCommentFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class TweetCommentAdapter extends RecyclerView.Adapter<TweetCommentAdapter.ViewHolder>  {
    Context context;
    List<TweetCommentResponse.CommentListBean> tCommentList;
    public TweetCommentAdapter( Context context,List<TweetCommentResponse.CommentListBean> tCommentList){
        this.context=context;
        this.tCommentList=tCommentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewHolder
        View view= LayoutInflater.from(context).inflate(R.layout.item_tweet_comment,parent,false);
        TweetCommentAdapter.ViewHolder vh=new TweetCommentAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(TweetCommentAdapter.ViewHolder holder, int position) {
        //绑定数据
        TweetCommentResponse.CommentListBean comment=tCommentList.get(position);
        holder.head.setImageURI(comment.getCommentPortrait());
        holder.name.setText(comment.getCommentAuthor());
        holder.time.setText(comment.getPubDate());
        holder.content.setText("原来如此！！！");

    }

    @Override
    public int getItemCount() {
        return tCommentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //取item所有控件
        SimpleDraweeView head;
        TextView content;
        TextView name;
        TextView time;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            item= (LinearLayout) itemView;
            head= (SimpleDraweeView) itemView.findViewById(R.id.image_tComment_head);
            content= (TextView) itemView.findViewById(R.id.tv_comment_content);
            name= (TextView) itemView.findViewById(R.id.tv_comment_name);
            time= (TextView) itemView.findViewById(R.id.tv_comment_time);
        }
    }
}
