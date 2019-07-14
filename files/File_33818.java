package com.example.jingbin.cloudreader.view.webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.http.utils.CheckNetwork;
import com.example.jingbin.cloudreader.ui.MainActivity;
import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.app.Constants;
import com.example.jingbin.cloudreader.data.UserUtil;
import com.example.jingbin.cloudreader.data.model.CollectModel;
import com.example.jingbin.cloudreader.utils.BaseTools;
import com.example.jingbin.cloudreader.utils.CommonUtils;
import com.example.jingbin.cloudreader.utils.DialogBuild;
import com.example.jingbin.cloudreader.utils.PermissionHandler;
import com.example.jingbin.cloudreader.utils.RxSaveImage;
import com.example.jingbin.cloudreader.utils.SPUtils;
import com.example.jingbin.cloudreader.utils.ShareUtils;
import com.example.jingbin.cloudreader.utils.ToastUtil;
import com.example.jingbin.cloudreader.view.statusbar.StatusBarUtil;
import com.example.jingbin.cloudreader.view.viewbigimage.ViewBigImageActivity;
import com.example.jingbin.cloudreader.view.webview.config.FullscreenHolder;
import com.example.jingbin.cloudreader.view.webview.config.IWebPageView;
import com.example.jingbin.cloudreader.view.webview.config.ImageClickInterface;
import com.example.jingbin.cloudreader.view.webview.config.MyWebChromeClient;
import com.example.jingbin.cloudreader.view.webview.config.MyWebViewClient;
import com.example.jingbin.cloudreader.viewmodel.wan.WanNavigator;

/**
 * 网页�?�以处�?�:
 * 点击相应控件:拨打电�?�?�?��?短信�?�?��?邮件�?上传图片�?播放视频
 * 进度�?��?返回网页上一层�?显示网页标题
 * Thanks to: https://github.com/youlookwhat/WebViewStudy
 * contact me: http://www.jianshu.com/users/e43c6e979831/latest_articles
 */
public class WebViewActivity extends AppCompatActivity implements IWebPageView {

    // 进度�?�
    private ProgressBar mProgressBar;
    private WebView webView;
    // 全�?时视频加载view
    private FrameLayout videoFullView;
    private Toolbar mTitleToolBar;
    // 加载视频相关
    private MyWebChromeClient mWebChromeClient;
    // title
    private String mTitle;
    // 网页链接
    private String mUrl;
    // �?�滚动的title 使用简�?� 没有�?�?�效果，文字两�?有阴影
    private TextView tvGunTitle;
    private boolean isTitleFix;
    private CollectModel collectModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getIntentData();
        initTitle();
        initWebView();
        webView.loadUrl(mUrl);
        getDataFromBrowser(getIntent());
    }

    private void getIntentData() {
        if (getIntent() != null) {
            mTitle = getIntent().getStringExtra("mTitle");
            mUrl = getIntent().getStringExtra("mUrl");
            isTitleFix = getIntent().getBooleanExtra("isTitleFix", false);
        }
    }

    private void initTitle() {
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.colorTheme), 0);
        mProgressBar = findViewById(R.id.pb_progress);
        webView = findViewById(R.id.webview_detail);
        videoFullView = findViewById(R.id.video_fullView);
        mTitleToolBar = findViewById(R.id.title_tool_bar);
        tvGunTitle = findViewById(R.id.tv_gun_title);

        initToolBar();
    }

    private void initToolBar() {
        setSupportActionBar(mTitleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
        mTitleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        tvGunTitle.postDelayed(() -> tvGunTitle.setSelected(true), 1900);
        tvGunTitle.setText(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_menu, menu);
        return true;
    }

    @Override
    public void setTitle(String mTitle) {
        if (!isTitleFix) {
            tvGunTitle.setText(mTitle);
            this.mTitle = mTitle;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 返回键
                handleFinish();
                break;
            case R.id.actionbar_share:
                // 分享到
                String shareText = mTitle + webView.getUrl() + " (分享自云阅)";
                ShareUtils.share(WebViewActivity.this, shareText);
                break;
            case R.id.actionbar_cope:
                // �?制链接
                BaseTools.copy(webView.getUrl());
                ToastUtil.showToast("�?制�?功");
                break;
            case R.id.actionbar_open:
                // 打开链接
                BaseTools.openLink(WebViewActivity.this, webView.getUrl());
                break;
            case R.id.actionbar_webview_refresh:
                // 刷新页�?�
                if (webView != null) {
                    webView.reload();
                }
                break;
            case R.id.actionbar_collect:
                // 添加到收�?
                if (UserUtil.isLogin(webView.getContext())) {
                    if (SPUtils.getBoolean(Constants.IS_FIRST_COLLECTURL, true)) {
                        DialogBuild.show(webView, "网�?��?�?�于文章，相�?�网�?��?�多次进行收�?，且�?会显示收�?状�?。", "知�?�了", (DialogInterface.OnClickListener) (dialog, which) -> {
                            SPUtils.putBoolean(Constants.IS_FIRST_COLLECTURL, false);
                            collectUrl();
                        });
                    } else {
                        collectUrl();
                    }
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void collectUrl() {
        // 收�?
        if (collectModel == null) {
            collectModel = new CollectModel();
        }
        collectModel.collectUrl(mTitle, webView.getUrl(), new WanNavigator.OnCollectNavigator() {
            @Override
            public void onSuccess() {
                ToastUtil.showToastLong("收�?网�?��?功");
            }

            @Override
            public void onFailure() {
                ToastUtil.showToastLong("收�?网�?�失败");
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mProgressBar.setVisibility(View.VISIBLE);
        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是�?��?�大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // �?存表�?�数�?�
        ws.setSaveFormData(true);
        // 是�?�应该支�?使用其�?幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // �?�动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模�?
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，�?�任�?比例缩放。
        ws.setUseWideViewPort(true);
        // �?缩放
        webView.setInitialScale(100);
        // 告诉WebView�?�用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        //  页�?�加载好以�?�，�?放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 排版适应�?幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是�?�新窗�?�打开(加了�?��?�能打�?开网页)
//        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认�?�?许混�?�模�?,https中�?能加载http资�?,需�?设置开�?�。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        /** 设置字体默认缩放大�?(改�?�网页字体大�?,setTextSize  api14被弃用)*/
        ws.setTextZoom(100);

        mWebChromeClient = new MyWebChromeClient(this);
        webView.setWebChromeClient(mWebChromeClient);
        // 与js交互
        webView.addJavascriptInterface(new ImageClickInterface(this), "injectedObject");
        webView.setWebViewClient(new MyWebViewClient(this));
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return handleLongImage();
            }
        });
    }

    @Override
    public void hindProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWebView() {
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindWebView() {
        webView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void fullViewAddView(View view) {
        FrameLayout decor = (FrameLayout) getWindow().getDecorView();
        videoFullView = new FullscreenHolder(WebViewActivity.this);
        videoFullView.addView(view);
        decor.addView(videoFullView);
    }

    @Override
    public void showVideoFullView() {
        videoFullView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hindVideoFullView() {
        videoFullView.setVisibility(View.GONE);
    }

    @Override
    public void startProgress(int newProgress) {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(newProgress);
        if (newProgress == 100) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void addImageClickListener() {
//        loadImageClickJS();
//        loadTextClickJS();
    }

    private void loadImageClickJS() {
        // 这段js函数的功能就是，�??历所有的img节点，并添加onclick函数，函数的功能是在图片点击的时候调用本地java接�?�并传递url过去
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){window.injectedObject.imageClick(this.getAttribute(\"src\"),this.getAttribute(\"has_link\"));}" +
                "}" +
                "})()");
    }

    private void loadTextClickJS() {
        // �??历所有的a节点,将节点里的属性传递过去(属性自定义,用于页�?�跳转)
        webView.loadUrl("javascript:(function(){" +
                "var objs =document.getElementsByTagName(\"a\");" +
                "for(var i=0;i<objs.length;i++)" +
                "{" +
                "objs[i].onclick=function(){" +
                "window.injectedObject.textClick(this.getAttribute(\"type\"),this.getAttribute(\"item_pk\"));}" +
                "}" +
                "})()");
    }

    public FrameLayout getVideoFullView() {
        return videoFullView;
    }

    /**
     * 全�?时按返加键执行退出全�?方法
     */
    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 上传图片之�?�的回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE) {
            mWebChromeClient.mUploadMessage(intent, resultCode);
        } else if (requestCode == MyWebChromeClient.FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            mWebChromeClient.mUploadMessageForAndroid5(intent, resultCode);
        }
    }

    /**
     * 使用singleTask�?�动模�?的Activity在系统中�?�会存在一个实例。
     * 如果这个实例已�?存在，intent就会通过onNewIntent传递到这个Activity。
     * �?�则新的Activity实例被创建。
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getDataFromBrowser(intent);
    }

    /**
     * 作为三方�?览器打开
     * Scheme: https
     * host: www.jianshu.com
     * path: /p/1cbaf784c29c
     * url = scheme + "://" + host + path;
     */
    private void getDataFromBrowser(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            try {
                String scheme = data.getScheme();
                String host = data.getHost();
                String path = data.getPath();
//                String text = "Scheme: " + scheme + "\n" + "host: " + host + "\n" + "path: " + path;
//                Log.e("data", text);
                String url = scheme + "://" + host + path;
                webView.loadUrl(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 长按图片事件处�?�
     */
    private boolean handleLongImage() {
        final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
        // 如果是图片类型或者是带有图片链接的类型
        if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            // 弹出�?存图片的对�?框
            new AlertDialog.Builder(WebViewActivity.this)
                    .setItems(new String[]{"查看大图", "�?存图片到相册"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String picUrl = hitTestResult.getExtra();
                            //获�?�图片
//                            Log.e("picUrl", picUrl);
                            switch (which) {
                                case 0:
                                    ViewBigImageActivity.start(WebViewActivity.this, picUrl, picUrl);
                                    break;
                                case 1:
                                    if (!PermissionHandler.isHandlePermission(WebViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                                        return;
                                    }
                                    RxSaveImage.saveImageToGallery(WebViewActivity.this, picUrl, picUrl);
                                    break;
                                default:
                                    break;
                            }
                        }
                    })
                    .show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //全�?播放退出全�?
            if (mWebChromeClient.inCustomView()) {
                hideCustomView();
                return true;

                //返回网页上一页
            } else if (webView.canGoBack()) {
                webView.goBack();
                return true;

                //退出网页
            } else {
                handleFinish();
            }
        }
        return false;
    }

    /**
     * 直接通过三方�?览器打开时，回退到首页
     */
    public void handleFinish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
        if (!MainActivity.isLaunch) {
            MainActivity.start(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        // 支付�?网页版在打开文章详情之�?�,无法点击按钮下一步
        webView.resumeTimers();
        // 设置为横�?
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onDestroy() {
        if (videoFullView != null) {
            videoFullView.clearAnimation();
            videoFullView.removeAllViews();
        }
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.removeAllViews();
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView = null;
            mProgressBar.clearAnimation();
            tvGunTitle.clearAnimation();
            tvGunTitle.clearFocus();
        }
        if (collectModel != null) {
            collectModel = null;
        }
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.fontScale != 1) {
            getResources();
        }
    }

    /**
     * �?止改�?�字体大�?
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 打开网页:
     *
     * @param mContext 上下文
     * @param mUrl     �?加载的网页url
     * @param mTitle   title
     */
    public static void loadUrl(Context mContext, String mUrl, String mTitle) {
        loadUrl(mContext, mUrl, mTitle, false);
    }

    /**
     * 打开网页:
     *
     * @param mContext     上下文
     * @param mUrl         �?加载的网页url
     * @param mTitle       title
     * @param isTitleFixed title是�?�固定
     */
    public static void loadUrl(Context mContext, String mUrl, String mTitle, boolean isTitleFixed) {
        if (CheckNetwork.isNetworkConnected(mContext)) {
            Intent intent = new Intent(mContext, WebViewActivity.class);
            intent.putExtra("mUrl", mUrl);
            intent.putExtra("isTitleFix", isTitleFixed);
            intent.putExtra("mTitle", mTitle == null ? "" : mTitle);
            mContext.startActivity(intent);
        } else {
            ToastUtil.showToastLong("当�?网络�?�?�用，请检查你的网络设置");
        }
    }

}
