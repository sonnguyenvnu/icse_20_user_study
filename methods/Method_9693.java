private boolean inTouchableArea(float x,float y){
  return Math.pow(x - mBorderRect.centerX(),2) + Math.pow(y - mBorderRect.centerY(),2) <= Math.pow(mBorderRadius,2);
}
