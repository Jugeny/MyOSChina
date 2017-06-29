package com.example.administrator.myoschina.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class MyVPAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public MyVPAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "开源咨询";
            case 1:
                return "推荐博客";
            case 2:
                return "技术问答";
            case 3:
                return "每日一搏";

        }
        return super.getPageTitle(position);
    }
}
