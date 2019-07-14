package com.example.jingbin.cloudreader.ui.menu;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.databinding.ActivityNavHomePageBinding;
import com.example.jingbin.cloudreader.utils.ShareUtils;
import com.example.jingbin.cloudreader.utils.StatusBarUtil;
import com.example.jingbin.cloudreader.utils.ToolbarHelper;

/**
 * @author jingbin
 */
public class NavHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 4.4 标题�?明
        StatusBarUtil.setTranslucentStatus(this);
        ActivityNavHomePageBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_nav_home_page);
        // 解决7.0以上系统 滑动到顶部 标题�?�?一�?�的问题
//        setSupportActionBar(binding.detailToolbar);
        ToolbarHelper.initFullBar(binding.detailToolbar, this);
        binding.detailToolbar.setNavigationIcon(null);

        binding.fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.share(v.getContext(), R.string.string_share_text);
            }
        });
    }

    public static void startHome(Context mContext) {
        Intent intent = new Intent(mContext, NavHomePageActivity.class);
        mContext.startActivity(intent);
    }
}
