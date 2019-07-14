private int calculateMeasuredWidth(){
  if (mRawWidth == 0) {
    mRawWidth=getMeasuredWidth();
  }
  return getMeasuredWidth() + calculateShadowWidth();
}
