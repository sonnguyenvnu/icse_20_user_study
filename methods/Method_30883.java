private void updateToolbarTitleAndSubtitle(){
  String url=mWebView.getUrl();
  if (TextUtils.isEmpty(url)) {
    return;
  }
  if (TextUtils.isEmpty(mTitleOrError)) {
    mTitleOrError=Uri.parse(url).getHost();
  }
  ActionBar actionBar=getSupportActionBar();
  if (TextUtils.isEmpty(actionBar.getSubtitle())) {
    mToolbar.setTitleTextAppearance(mToolbar.getContext(),R.style.TextAppearance_Widget_Douya_Toolbar_Title_WebView);
    mToolbar.setSubtitleTextAppearance(mToolbar.getContext(),R.style.TextAppearance_Widget_Douya_Toolbar_Subtitle_WebView);
  }
  setTitle(mTitleOrError);
  if (Settings.CREATE_NEW_TASK_FOR_WEBVIEW.getValue()) {
    AppUtils.setTaskDescriptionLabel(this,mTitleOrError);
  }
  actionBar.setSubtitle(url);
}
