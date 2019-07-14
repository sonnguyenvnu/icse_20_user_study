public static DisplayMetrics getScreenSize(Context context){
  DisplayMetrics displaysMetrics=new DisplayMetrics();
  context.getResources().getDisplayMetrics();
  WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
  wm.getDefaultDisplay().getMetrics(displaysMetrics);
  return displaysMetrics;
}
