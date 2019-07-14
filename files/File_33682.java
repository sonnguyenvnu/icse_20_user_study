package com.example.jingbin.cloudreader.ui.wan.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseActivity;
import com.example.jingbin.cloudreader.databinding.ActivityMyCollectBinding;
import com.example.jingbin.cloudreader.view.MyFragmentPagerAdapter;
import com.example.jingbin.cloudreader.viewmodel.menu.NoViewModel;

import java.util.ArrayList;

/**
 * çŽ©å®‰å?“æ”¶è—?
 *
 * @author jingbin
 */
public class MyCollectActivity extends BaseActivity<NoViewModel, ActivityMyCollectBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        showLoading();
        setTitle("æˆ‘çš„æ”¶è—?");
        initFragmentList();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        bindingView.vpMyCollect.setAdapter(myAdapter);
        bindingView.vpMyCollect.setOffscreenPageLimit(2);
        myAdapter.notifyDataSetChanged();
        bindingView.tabMyCollect.setupWithViewPager(bindingView.vpMyCollect);
        showContentView();
    }

    private void initFragmentList() {
        mTitleList.clear();
        mTitleList.add("æ–‡ç« ");
        mTitleList.add("ç½‘å?€");
        mTitleList.add("æ®µå­?");
        mFragments.add(CollectArticleFragment.newInstance());
        mFragments.add(CollectLinkFragment.newInstance());
        mFragments.add(JokeFragment.newInstance("æ®µå­?"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTitleList.clear();
        mFragments.clear();
        mTitleList = null;
        mFragments = null;
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, MyCollectActivity.class);
        mContext.startActivity(intent);
    }
}
