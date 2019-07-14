@SuppressLint("SetJavaScriptEnabled") private void initView(@Nullable AttributeSet attrs){
  if (isInEditMode())   return;
  if (attrs != null) {
    TypedArray tp=getContext().obtainStyledAttributes(attrs,R.styleable.PrettifyWebView);
    try {
      int color=tp.getColor(R.styleable.PrettifyWebView_webview_background,ViewHelper.getWindowBackground(getContext()));
      setBackgroundColor(color);
    }
  finally {
      tp.recycle();
    }
  }
  setWebChromeClient(new ChromeClient());
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    setWebViewClient(new WebClient());
  }
 else {
    setWebViewClient(new WebClientCompat());
  }
  WebSettings settings=getSettings();
  settings.setJavaScriptEnabled(true);
  settings.setAppCachePath(getContext().getCacheDir().getPath());
  settings.setAppCacheEnabled(true);
  settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
  settings.setDefaultTextEncodingName("utf-8");
  settings.setLoadsImagesAutomatically(true);
  settings.setBlockNetworkImage(false);
  setOnLongClickListener((view) -> {
    WebView.HitTestResult result=getHitTestResult();
    if (hitLinkResult(result) && !InputHelper.isEmpty(result.getExtra())) {
      AppHelper.copyToClipboard(getContext(),result.getExtra());
      return true;
    }
    return false;
  }
);
}
