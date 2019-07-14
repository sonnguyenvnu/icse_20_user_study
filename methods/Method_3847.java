@Override boolean shouldMeasureTwice(){
  return getHeightMode() != View.MeasureSpec.EXACTLY && getWidthMode() != View.MeasureSpec.EXACTLY && hasFlexibleChildInBothOrientations();
}
