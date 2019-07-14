private int calculateMeasuredWidth(){
  int width=getCircleSize() + calculateShadowWidth();
  if (mProgressBarEnabled) {
    width+=mProgressWidth * 2;
  }
  return width;
}
