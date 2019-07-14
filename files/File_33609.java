package com.example.jingbin.cloudreader.ui.film;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseFragment;
import com.example.jingbin.cloudreader.databinding.FragmentGankBinding;
import com.example.jingbin.cloudreader.ui.film.child.FilmComingFragment;
import com.example.jingbin.cloudreader.ui.film.child.FilmShowingFragment;
import com.example.jingbin.cloudreader.view.MyFragmentPagerAdapter;
import com.example.jingbin.cloudreader.viewmodel.menu.NoViewModel;

import java.util.ArrayList;

/**
 * Created by jingbin on 19/05/16.
 * 展示热映榜和�?�将上映的页�?�
 */
public class FilmFragment extends BaseFragment<NoViewModel, FragmentGankBinding> {

    private ArrayList<String> mTitleList = new ArrayList<>(2);
    private ArrayList<Fragment> mFragments = new ArrayList<>(2);
    private boolean mIsFirst = true;
    private boolean mIsPrepared;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showContentView();
        mIsPrepared = true;
    }

    @Override
    protected void loadData() {
        if (!mIsPrepared || !mIsVisible || !mIsFirst) {
            return;
        }
        bindingView.vpGank.postDelayed(() -> {
            initFragmentList();
            MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
            bindingView.vpGank.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
            bindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
            bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
            mIsFirst = false;
        }, 110);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_gank;
    }

    private void initFragmentList() {
        mTitleList.clear();
        mTitleList.add("热映榜");
        mTitleList.add("�?�将上映");
        mFragments.add(FilmShowingFragment.newInstance("热映榜"));
        mFragments.add(FilmComingFragment.newInstance("�?�将上映"));
//        mTitleList.add("电影");
//        mTitleList.add("书�?");
//        mFragments.add(new OneFragment());
//        mFragments.add(BookListFragment.newInstance("综�?�"));
    }

}
