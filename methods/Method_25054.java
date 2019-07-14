private int calculateMeasuredHeight(){
  int height=getCircleSize() + calculateShadowHeight();
  if (mProgressBarEnabled) {
    height+=mProgressWidth * 2;
  }
  return height;
}
