@SuppressLint({"SetJavaScriptEnabled","AddJavascriptInterface"}) @Override public View createView(Context context){
  swipeBackEnabled=false;
  actionBar.setBackButtonImage(R.drawable.ic_ab_back);
  actionBar.setAllowOverlayTitle(true);
  actionBar.setActionBarMenuOnItemClick(new ActionBar.ActionBarMenuOnItemClick(){
    @Override public void onItemClick(    int id){
      if (id == -1) {
        finishFragment();
      }
 else       if (id == share) {
        if (currentMessageObject != null) {
          currentMessageObject.messageOwner.with_my_score=false;
          showDialog(ShareAlert.createShareAlert(getParentActivity(),currentMessageObject,null,false,linkToCopy,false));
        }
      }
 else       if (id == open_in) {
        openGameInBrowser(currentUrl,currentMessageObject,getParentActivity(),short_param,currentBot);
      }
    }
  }
);
  ActionBarMenu menu=actionBar.createMenu();
  progressItem=menu.addItemWithWidth(share,R.drawable.share,AndroidUtilities.dp(54));
  if (type == TYPE_GAME) {
    ActionBarMenuItem menuItem=menu.addItem(0,R.drawable.ic_ab_other);
    menuItem.addSubItem(open_in,R.drawable.msg_openin,LocaleController.getString("OpenInExternalApp",R.string.OpenInExternalApp));
    actionBar.setTitle(currentGame);
    actionBar.setSubtitle("@" + currentBot);
    progressView=new ContextProgressView(context,1);
    progressItem.addView(progressView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    progressView.setAlpha(0.0f);
    progressView.setScaleX(0.1f);
    progressView.setScaleY(0.1f);
    progressView.setVisibility(View.INVISIBLE);
  }
 else   if (type == TYPE_STAT) {
    actionBar.setBackgroundColor(Theme.getColor(Theme.key_player_actionBar));
    actionBar.setItemsColor(Theme.getColor(Theme.key_player_actionBarItems),false);
    actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_player_actionBarSelector),false);
    actionBar.setTitleColor(Theme.getColor(Theme.key_player_actionBarTitle));
    actionBar.setSubtitleColor(Theme.getColor(Theme.key_player_actionBarSubtitle));
    actionBar.setTitle(LocaleController.getString("Statistics",R.string.Statistics));
    progressView=new ContextProgressView(context,3);
    progressItem.addView(progressView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    progressView.setAlpha(1.0f);
    progressView.setScaleX(1.0f);
    progressView.setScaleY(1.0f);
    progressView.setVisibility(View.VISIBLE);
    progressItem.getImageView().setVisibility(View.GONE);
    progressItem.setEnabled(false);
  }
  webView=new WebView(context);
  webView.getSettings().setJavaScriptEnabled(true);
  webView.getSettings().setDomStorageEnabled(true);
  fragmentView=new FrameLayout(context);
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  if (Build.VERSION.SDK_INT >= 19) {
    webView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
  }
  if (Build.VERSION.SDK_INT >= 21) {
    webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    CookieManager cookieManager=CookieManager.getInstance();
    cookieManager.setAcceptThirdPartyCookies(webView,true);
    if (type == TYPE_GAME) {
      webView.addJavascriptInterface(new TelegramWebviewProxy(),"TelegramWebviewProxy");
    }
  }
  webView.setWebViewClient(new WebViewClient(){
    private boolean isInternalUrl(    String url){
      if (TextUtils.isEmpty(url)) {
        return false;
      }
      Uri uri=Uri.parse(url);
      if ("tg".equals(uri.getScheme())) {
        if (type == TYPE_STAT) {
          try {
            uri=Uri.parse(url.replace("tg:statsrefresh","tg://telegram.org"));
            reloadStats(uri.getQueryParameter("params"));
          }
 catch (          Throwable e) {
            FileLog.e(e);
          }
        }
 else {
          finishFragment(false);
          try {
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            ComponentName componentName=new ComponentName(ApplicationLoader.applicationContext.getPackageName(),LaunchActivity.class.getName());
            intent.setComponent(componentName);
            intent.putExtra(android.provider.Browser.EXTRA_APPLICATION_ID,ApplicationLoader.applicationContext.getPackageName());
            ApplicationLoader.applicationContext.startActivity(intent);
          }
 catch (          Exception e) {
            FileLog.e(e);
          }
        }
        return true;
      }
      return false;
    }
    @Override public void onLoadResource(    WebView view,    String url){
      if (isInternalUrl(url)) {
        return;
      }
      super.onLoadResource(view,url);
    }
    @Override public boolean shouldOverrideUrlLoading(    WebView view,    String url){
      return isInternalUrl(url) || super.shouldOverrideUrlLoading(view,url);
    }
    @Override public void onPageFinished(    WebView view,    String url){
      super.onPageFinished(view,url);
      if (progressView != null && progressView.getVisibility() == View.VISIBLE) {
        AnimatorSet animatorSet=new AnimatorSet();
        if (type == TYPE_GAME) {
          progressItem.getImageView().setVisibility(View.VISIBLE);
          progressItem.setEnabled(true);
          animatorSet.playTogether(ObjectAnimator.ofFloat(progressView,"scaleX",1.0f,0.1f),ObjectAnimator.ofFloat(progressView,"scaleY",1.0f,0.1f),ObjectAnimator.ofFloat(progressView,"alpha",1.0f,0.0f),ObjectAnimator.ofFloat(progressItem.getImageView(),"scaleX",0.0f,1.0f),ObjectAnimator.ofFloat(progressItem.getImageView(),"scaleY",0.0f,1.0f),ObjectAnimator.ofFloat(progressItem.getImageView(),"alpha",0.0f,1.0f));
        }
 else {
          animatorSet.playTogether(ObjectAnimator.ofFloat(progressView,"scaleX",1.0f,0.1f),ObjectAnimator.ofFloat(progressView,"scaleY",1.0f,0.1f),ObjectAnimator.ofFloat(progressView,"alpha",1.0f,0.0f));
        }
        animatorSet.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animator){
            if (type == TYPE_STAT) {
              progressItem.setVisibility(View.GONE);
            }
 else {
              progressView.setVisibility(View.INVISIBLE);
            }
          }
        }
);
        animatorSet.setDuration(150);
        animatorSet.start();
      }
    }
  }
);
  frameLayout.addView(webView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  return fragmentView;
}
