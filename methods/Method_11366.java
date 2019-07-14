/** 
 * Returns the minimum allowed scale.
 */
private float minScale(){
  int vPadding=getPaddingBottom() + getPaddingTop();
  int hPadding=getPaddingLeft() + getPaddingRight();
  if (minimumScaleType == SCALE_TYPE_CENTER_CROP) {
    return Math.max((getWidth() - hPadding) / (float)sWidth(),(getHeight() - vPadding) / (float)sHeight());
  }
 else   if (minimumScaleType == SCALE_TYPE_CUSTOM && minScale > 0) {
    return minScale;
  }
 else {
    return Math.min((getWidth() - hPadding) / (float)sWidth(),(getHeight() - vPadding) / (float)sHeight());
  }
}
