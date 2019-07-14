public float getRectY(){
  return cropView.getCropTop() - AndroidUtilities.dp(14) - (Build.VERSION.SDK_INT >= 21 ? AndroidUtilities.statusBarHeight : 0);
}
