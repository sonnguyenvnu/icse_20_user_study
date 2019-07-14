package com.example.jingbin.cloudreader.view.webview.config;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jingbin.cloudreader.utils.BaseTools;
import com.example.jingbin.cloudreader.utils.CheckNetwork;
import com.example.jingbin.cloudreader.view.webview.WebViewActivity;


/**
 * Created by jingbin on 2016/11/17.
 * 监�?�网页链接:
 * - 优酷视频直接跳到自带�?览器
 * - 根�?�标识:打电�?�?�?�短信�?�?�邮件
 * - 进度�?�的显示
 * - 添加javascript监�?�
 */
public class MyWebViewClient extends WebViewClient {

    private IWebPageView mIWebPageView;
    private WebViewActivity mActivity;

    public MyWebViewClient(IWebPageView mIWebPageView) {
        this.mIWebPageView = mIWebPageView;
        mActivity = (WebViewActivity) mIWebPageView;

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        DebugUtil.error("----url:"+url);
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        if (url.startsWith("http:") || url.startsWith("https:")) {
            // �?�能有�??示下载Apk文件
            if (url.contains(".apk")) {
                handleOtherwise(mActivity, url);
                return true;
            }
            return false;
        }

        handleOtherwise(mActivity, url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (!CheckNetwork.isNetworkConnected(mActivity)) {
            mIWebPageView.hindProgressBar();
        }
        // html加载完�?之�?�，添加监�?�图片的点击js函数
        mIWebPageView.addImageClickListener();
        super.onPageFinished(view, url);
    }

    // 视频全�?播放按返回页�?�被放大的问题
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        if (newScale - oldScale > 7) {
            view.setInitialScale((int) (oldScale / newScale * 100)); //异常放大，缩回去。
        }
    }

    /**
     * 网页里�?�能唤起其他的app
     */
    private void handleOtherwise(Activity activity, String url) {
        String appPackageName = "";
        // 支付�?支付
        if (url.contains("alipays")) {
            appPackageName = "com.eg.android.AlipayGphone";

            // 微信支付
        } else if (url.contains("weixin://wap/pay")) {
            appPackageName = "com.tencent.mm";

            // 京东产�?详情
        } else if (url.contains("openapp.jdmobile")) {
            appPackageName = "com.jingdong.app.mall";
        } else {
            startActivity(url);
        }
        if (BaseTools.isApplicationAvilible(activity, appPackageName)) {
            startActivity(url);
        }
    }

    private void startActivity(String url) {
        try {
            Intent intent1 = new Intent();
            intent1.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent1.setData(uri);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mActivity.startActivity(intent1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
