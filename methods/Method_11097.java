private void calPixels(){
  if (activity != null && metrics != null) {
    activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    int[] location=new int[2];
    getLocationInWindow(location);
    bottomHeight=metrics.heightPixels - location[1];
  }
}
