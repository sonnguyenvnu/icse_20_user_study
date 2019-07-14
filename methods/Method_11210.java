@Override protected void drawProgress(LinearLayout layoutProgress,float max,float progress,float totalWidth,int radius,int padding,int colorProgress,boolean isReverse){
  GradientDrawable backgroundDrawable=createGradientDrawable(colorProgress);
  int newRadius=radius - (padding / 2);
  backgroundDrawable.setCornerRadii(new float[]{newRadius,newRadius,newRadius,newRadius,newRadius,newRadius,newRadius,newRadius});
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    layoutProgress.setBackground(backgroundDrawable);
  }
 else {
    layoutProgress.setBackgroundDrawable(backgroundDrawable);
  }
  float ratio=max / progress;
  int progressWidth=(int)((totalWidth - (padding * 2)) / ratio);
  ViewGroup.LayoutParams progressParams=layoutProgress.getLayoutParams();
  progressParams.width=progressWidth;
  layoutProgress.setLayoutParams(progressParams);
}
