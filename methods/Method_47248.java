private float getRelativeTouchPosition(MotionEvent event){
  float yInParent=event.getRawY() - Utils.getViewRawY(handle);
  return yInParent / (getHeightMinusPadding() - handle.getHeight());
}
