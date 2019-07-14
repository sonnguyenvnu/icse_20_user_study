package com.example.jingbin.cloudreader.ui.wan.child;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.base.BaseActivity;
import com.example.jingbin.cloudreader.databinding.ActivityLoginBinding;
import com.example.jingbin.cloudreader.viewmodel.menu.LoginViewModel;

/**
 * 玩安�?�登录
 *
 * @author jingbin
 */
public class LoginActivity extends BaseActivity<LoginViewModel, ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("玩安�?�登录");
        showContentView();
        bindingView.setViewModel(viewModel);
    }

    public void register(View view) {
        viewModel.register().observe(this, this::loadSuccess);
    }

    public void login(View view) {
        viewModel.login().observe(this, this::loadSuccess);
    }

    /**
     * 注册或登录�?功
     */
    public void loadSuccess(Boolean aBoolean) {
        if (aBoolean != null && aBoolean) {
            finish();
        }
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
    }
}
