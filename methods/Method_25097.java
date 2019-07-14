private int calculateMeasuredHeight(){
  if (mRawHeight == 0) {
    mRawHeight=getMeasuredHeight();
  }
  return getMeasuredHeight() + calculateShadowHeight();
}
