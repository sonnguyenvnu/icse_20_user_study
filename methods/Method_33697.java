public static int getWindowHeigh(Context context){
  WindowManager wm=(WindowManager)(context.getSystemService(Context.WINDOW_SERVICE));
  DisplayMetrics dm=new DisplayMetrics();
  wm.getDefaultDisplay().getMetrics(dm);
  int mScreenHeigh=dm.heightPixels;
  return mScreenHeigh;
}
