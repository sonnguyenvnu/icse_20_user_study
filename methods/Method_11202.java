@SuppressWarnings("deprecation") @Override protected void drawProgress(LinearLayout layoutProgress,float max,float progress,float totalWidth,int radius,int padding,int colorProgress,boolean isReverse){
  GradientDrawable backgroundDrawable=createGradientDrawable(colorProgress);
  int newRadius=radius - (padding / 2);
  if (isReverse && progress != max) {
    backgroundDrawable.setCornerRadii(new float[]{newRadius,newRadius,newRadius,newRadius,newRadius,newRadius,newRadius,newRadius});
  }
 else {
    backgroundDrawable.setCornerRadii(new float[]{0,0,newRadius,newRadius,newRadius,newRadius,0,0});
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    layoutProgress.setBackground(backgroundDrawable);
  }
 else {
    layoutProgress.setBackgroundDrawable(backgroundDrawable);
  }
  float ratio=max / progress;
  int progressWidth=(int)((totalWidth - ((padding * 2) + ivProgressIcon.getWidth())) / ratio);
  ViewGroup.LayoutParams progressParams=layoutProgress.getLayoutParams();
  progressParams.width=progressWidth;
  layoutProgress.setLayoutParams(progressParams);
}
