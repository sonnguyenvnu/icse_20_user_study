private void initTitle(){
  StatusBarUtil.setColor(this,CommonUtils.getColor(R.color.colorTheme),0);
  mProgressBar=findViewById(R.id.pb_progress);
  webView=findViewById(R.id.webview_detail);
  videoFullView=findViewById(R.id.video_fullView);
  mTitleToolBar=findViewById(R.id.title_tool_bar);
  tvGunTitle=findViewById(R.id.tv_gun_title);
  initToolBar();
}
