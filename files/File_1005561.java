package com.sankuai.waimai.router.demo.basic;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultUriRequest;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.UriRequest;
import com.sankuai.waimai.router.demo.R;
import com.sankuai.waimai.router.demo.lib2.BaseActivity;
import com.sankuai.waimai.router.demo.lib2.DemoConstant;
import com.sankuai.waimai.router.demo.lib2.ToastUtils;

/**
 * 基本用法Demo
 * <p>
 * Created by jzj on 2018/4/19.
 */

public class MainActivity extends BaseActivity {

    public static final String[] URIS = {
            // 基本页�?�跳转，支�?�?�?置Scheme�?Host，支�?多个path
            DemoConstant.JUMP_ACTIVITY_1,
            DemoConstant.JUMP_ACTIVITY_2,

            // Kotlin
            DemoConstant.KOTLIN,

            // request跳转测试
            DemoConstant.JUMP_WITH_REQUEST,

            // 自定义Scheme�?Host测试；外部跳转测试
            DemoConstant.DEMO_SCHEME + "://" + DemoConstant.DEMO_HOST
                    + DemoConstant.EXPORTED_PATH,
            DemoConstant.DEMO_SCHEME + "://" + DemoConstant.DEMO_HOST
                    + DemoConstant.NOT_EXPORTED_PATH,

            // Library工程测试
            DemoConstant.TEST_LIB1,
            DemoConstant.TEST_LIB2,

            // 拨打电�?
            DemoConstant.TEL,

            // �?级策略
            DemoConstant.TEST_NOT_FOUND,

            // Fragment test
            DemoConstant.JUMP_FRAGMENT_ACTIVITY,

            // Fragment to Fragment test
            DemoConstant.TEST_FRAGMENT_TO_FRAGMENT_ACTIVITY,

            // 高级Demo页�?�
            DemoConstant.ADVANCED_DEMO,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout container = findViewById(R.id.layout_container);
        for (final String uri : URIS) {
            Button button = new Button(this);
            button.setAllCaps(false);
            button.setText(uri);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    jump(uri);
                }
            });
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            container.addView(button, params);
        }
    }

    private void jump(String uri) {
        if (DemoConstant.JUMP_WITH_REQUEST.equals(uri)) {
            new DefaultUriRequest(this, uri)
                    .activityRequestCode(100)
                    .putExtra(TestUriRequestActivity.INTENT_TEST_INT, 1)
                    .putExtra(TestUriRequestActivity.INTENT_TEST_STR, "str")
                    .overridePendingTransition(R.anim.enter_activity, R.anim.exit_activity)
                    .onComplete(new OnCompleteListener() {
                        @Override
                        public void onSuccess(@NonNull UriRequest request) {
                            ToastUtils.showToast(request.getContext(), "跳转�?功");
                        }

                        @Override
                        public void onError(@NonNull UriRequest request, int resultCode) {

                        }
                    })
                    .start();
        } else {
            Router.startUri(this, uri);
        }
    }
}
