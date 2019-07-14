/** 
 * ?ViewPager???????
 * @param direction
 * @return
 */
@Override public boolean canScrollHorizontally(int direction){
  if (mPinchMode == PinchImageView.PINCH_MODE_SCALE) {
    return true;
  }
  RectF bound=getImageBound(null);
  if (bound == null) {
    return false;
  }
  if (bound.isEmpty()) {
    return false;
  }
  if (direction > 0) {
    return bound.right > getWidth();
  }
 else {
    return bound.left < 0;
  }
}
