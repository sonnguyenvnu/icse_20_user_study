@TargetApi(Build.VERSION_CODES.KITKAT) @Override public void setContentView(int layoutResID){
  super.setContentView(layoutResID);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  }
  SystemBarTintManager tintManager=new SystemBarTintManager(this);
  tintManager.setStatusBarTintEnabled(true);
  tintManager.setStatusBarTintResource(R.color.topbar_bg);
  pbBaseTitle=(ProgressBar)findViewById(R.id.pbBaseTitle);
  tvBaseTitle=(TextView)findViewById(R.id.tvBaseTitle);
}
