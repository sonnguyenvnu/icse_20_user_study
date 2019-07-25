public static void immersive(Window window,int color,@FloatRange(from=0.0,to=1.0) float alpha){
  if (Build.VERSION.SDK_INT >= 21) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(mixtureColor(color,alpha));
    int systemUiVisibility=window.getDecorView().getSystemUiVisibility();
    systemUiVisibility|=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    systemUiVisibility|=View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    window.getDecorView().setSystemUiVisibility(systemUiVisibility);
  }
 else   if (Build.VERSION.SDK_INT >= 19) {
    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    setTranslucentView((ViewGroup)window.getDecorView(),color,alpha);
  }
 else   if (Build.VERSION.SDK_INT >= MIN_API && Build.VERSION.SDK_INT > 16) {
    int systemUiVisibility=window.getDecorView().getSystemUiVisibility();
    systemUiVisibility|=View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
    systemUiVisibility|=View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
    window.getDecorView().setSystemUiVisibility(systemUiVisibility);
  }
}
