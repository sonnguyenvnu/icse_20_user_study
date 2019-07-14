package com.vondear.rxui.activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vondear.rxtool.RxBarTool;
import com.vondear.rxtool.RxConstants;
import com.vondear.rxtool.RxImageTool;
import com.vondear.rxtool.RxKeyboardTool;
import com.vondear.rxui.R;
import com.vondear.rxui.view.RxTextAutoZoom;

/**
 * @author vondear
 */
public class ActivityWebView extends ActivityBase {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    ProgressBar pbWebBase;
    TextView tvTitle;
    WebView webBase;
    ImageView ivFinish;
    RxTextAutoZoom mRxTextAutoZoom;
    LinearLayout llIncludeTitle;
    private String webPath = "";
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBarTool.setTransparentStatusBar(this);
        setContentView(R.layout.activity_webview);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();// �?始化控件 - FindViewById之类的�?作
        initData();// �?始化控件的数�?��?�监�?�事件
    }

    private void initView() {
        // TODO Auto-generated method stub
        mRxTextAutoZoom = findViewById(R.id.afet_tv_title);
        llIncludeTitle = findViewById(R.id.ll_include_title);
        tvTitle = findViewById(R.id.tv_title);
        pbWebBase = findViewById(R.id.pb_web_base);
        webBase = findViewById(R.id.web_base);
        ivFinish = findViewById(R.id.iv_finish);
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webBase.canGoBack()) {
                    webBase.goBack();
                } else {
                    finish();
                }
            }
        });

        initAutoFitEditText();
    }

    public void initAutoFitEditText() {

        mRxTextAutoZoom.clearFocus();
        mRxTextAutoZoom.setEnabled(false);
        mRxTextAutoZoom.setFocusableInTouchMode(false);
        mRxTextAutoZoom.setFocusable(false);
        mRxTextAutoZoom.setEnableSizeCache(false);
        //might cause crash on some devices
        mRxTextAutoZoom.setMovementMethod(null);
        // can be added after layout inflation;
        mRxTextAutoZoom.setMaxHeight(RxImageTool.dip2px(55f));
        //don't forget to add min text size programmatically
        mRxTextAutoZoom.setMinTextSize(37f);

        RxTextAutoZoom.setNormalization(this, llIncludeTitle, mRxTextAutoZoom);

        RxKeyboardTool.hideSoftInput(this);
    }

    private void initData() {
        pbWebBase.setMax(100);//设置加载进度最大值
//        webPath = getIntent().getStringExtra("URL");
        webPath = RxConstants.URL_BAIDU_SEARCH;//加载的URL
        if (webPath.equals("")) {
            webPath = "http://www.baidu.com";
        }
        WebSettings webSettings = webBase.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存�?�则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webBase.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解�?
        }
        webBase.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解�?

//        webSettings.setAllowContentAccess(true);
//        webSettings.setAllowFileAccessFromFileURLs(true);
//        webSettings.setAppCacheEnabled(true);
   /*     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }*/


        // setMediaPlaybackRequiresUserGesture(boolean require) //是�?�需�?用户手势�?�播放Media，默认true

        webSettings.setJavaScriptEnabled(true); // 设置支�?javascript脚本
//        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportZoom(true);// 设置�?�以支�?缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是�?�使用WebView内置的缩放组件，由浮动在窗�?�上的缩放控制和手势缩放控制组�?，默认false

        webSettings.setDisplayZoomControls(false);//�?�?缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应�?幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//�?存密�?
        webSettings.setDomStorageEnabled(true);//是�?�开�?�本地DOM存储  鉴于它的安全特性（任何人都能读�?�到它，尽管有相应的�?制，将�?感数�?�存储在这里�?然�?是明智之举），Android 默认是关闭该功能的。
        webBase.setSaveEnabled(true);
        webBase.setKeepScreenOn(true);


        // 设置setWebChromeClient对象
        webBase.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mRxTextAutoZoom.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                pbWebBase.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法�?�在WebView中打开链接，�??之用�?览器打开
        webBase.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webBase.getSettings().getLoadsImagesAutomatically()) {
                    webBase.getSettings().setLoadsImagesAutomatically(true);
                }
                pbWebBase.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                pbWebBase.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }


                // Otherwise allow the OS to handle things like tel, mailto, etc.
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        webBase.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(paramAnonymousString1));
                startActivity(intent);
            }
        });

        webBase.loadUrl(webPath);
        Log.v("帮助类完整连接", webPath);
//        webBase.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,webBase.getHeight()));
    }

    @Override
    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putString("url", webBase.getUrl());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        try {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_LANDSCAPE");
            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_PORTRAIT");
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void onBackPressed() {

        if (webBase.canGoBack()) {
            webBase.goBack();
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "�?次点击返回键退出", Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
    }

}

