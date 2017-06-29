package com.example.administrator.myoschina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myoschina.R;
import com.example.administrator.myoschina.bean.PostResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class PostRVAdapter extends RecyclerView.Adapter<PostRVAdapter.ViewHolder>{
    //1.数据源 2.item布局
    Context context;
    List<PostResponse.PostListBean> postList;

    public PostRVAdapter(Context context, List<PostResponse.PostListBean> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建viewHolder
        View view= LayoutInflater.from(context).inflate(R.layout.item_post,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据
        PostResponse.PostListBean post=postList.get(position);
        holder.head.setImageURI(post.getPortrait());
        holder.title.setText(post.getTitle());
        holder.content.setText("问题回答大大的啥感觉更好看刷卡");
        holder.author.setText("@"+post.getAuthor());
        holder.time.setText(post.getPubDate());
        holder.count.setText(post.getAnswerCount()+"");


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //取item所有控件
        SimpleDraweeView head;
        TextView title;
        TextView content;
        TextView author;
        TextView time;
        TextView count;
        LinearLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            item= (LinearLayout) itemView;
            head= (SimpleDraweeView) itemView.findViewById(R.id.image_post_head);
            title= (TextView) itemView.findViewById(R.id.tv_post_title);
            content= (TextView) itemView.findViewById(R.id.tv_post_content);
            author= (TextView) itemView.findViewById(R.id.tv_post_author);
            time= (TextView) itemView.findViewById(R.id.tv_post_time);
            count= (TextView) itemView.findViewById(R.id.tv_post_count);
        }
    }
}
