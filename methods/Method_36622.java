public static int getWindowWidth(Activity context){
  DisplayMetrics metric=new DisplayMetrics();
  context.getWindowManager().getDefaultDisplay().getMetrics(metric);
  return metric.widthPixels;
}
