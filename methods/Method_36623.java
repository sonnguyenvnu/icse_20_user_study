public static int getWindowHeight(Activity context){
  DisplayMetrics metric=new DisplayMetrics();
  context.getWindowManager().getDefaultDisplay().getMetrics(metric);
  return metric.heightPixels;
}
