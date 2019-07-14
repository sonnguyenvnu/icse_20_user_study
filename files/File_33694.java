package com.example.jingbin.cloudreader.ui.wan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseFragment;
import com.example.jingbin.cloudreader.databinding.FragmentBookBinding;
import com.example.jingbin.cloudreader.ui.wan.child.HomeFragment;
import com.example.jingbin.cloudreader.ui.wan.child.NaviFragment;
import com.example.jingbin.cloudreader.ui.wan.child.TreeFragment;
import com.example.jingbin.cloudreader.view.MyFragmentPagerAdapter;
import com.example.jingbin.cloudreader.viewmodel.menu.NoViewModel;

import java.util.ArrayList;

/**
 * Created by jingbin on 16/12/14.
 * 展示玩安�?�的页�?�
 */
public class WanFragment extends BaseFragment<NoViewModel,FragmentBookBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showLoading();
        initFragmentList();
        /**
         * 注�?使用的是：getChildFragmentManager，
         * 这样setOffscreenPageLimit()就�?�以添加上，�?留相邻2个实例，切�?�时�?会�?�
         * 但会内存溢出，在显示时加载数�?�
         */
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpBook.setAdapter(myAdapter);
        // 左�?�预加载页�?�的个数
        bindingView.vpBook.setOffscreenPageLimit(2);
        myAdapter.notifyDataSetChanged();
        bindingView.tabBook.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabBook.setupWithViewPager(bindingView.vpBook);
        showContentView();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_book;
    }

    private void initFragmentList() {
        mTitleList.clear();
        mTitleList.add("玩安�?�");
        mTitleList.add("知识体系");
        mTitleList.add("导航数�?�");
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(TreeFragment.newInstance());
        mFragments.add(NaviFragment.newInstance());
    }
}
