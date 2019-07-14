public static int getWindowWidth(Context context){
  WindowManager wm=(WindowManager)(context.getSystemService(Context.WINDOW_SERVICE));
  DisplayMetrics dm=new DisplayMetrics();
  wm.getDefaultDisplay().getMetrics(dm);
  int mScreenWidth=dm.widthPixels;
  return mScreenWidth;
}
