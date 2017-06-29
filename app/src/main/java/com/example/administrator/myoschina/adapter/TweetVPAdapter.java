package com.example.administrator.myoschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/9.
 */

public class TweetVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public TweetVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "赞";
            case 1:
                return "评论";
        }
        return super.getPageTitle(position);
    }
}
