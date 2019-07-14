public void setBackground(Drawable drawable,boolean withRound,boolean animated){
  lastUpdateTime=System.currentTimeMillis();
  if (animated && currentDrawable != drawable) {
    previousDrawable=currentDrawable;
    previousWithRound=currentWithRound;
    animatedAlphaValue=1.0f;
    setProgress(1,animated);
  }
 else {
    previousDrawable=null;
    previousWithRound=false;
  }
  currentWithRound=withRound;
  currentDrawable=drawable;
  if (!animated) {
    parent.invalidate();
  }
 else {
    invalidateParent();
  }
}
