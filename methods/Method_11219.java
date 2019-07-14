@Override public void onGlobalLayout(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    tvProgress.getViewTreeObserver().removeOnGlobalLayoutListener(this);
  }
 else {
    tvProgress.getViewTreeObserver().removeGlobalOnLayoutListener(this);
  }
  drawTextProgressPosition();
}
