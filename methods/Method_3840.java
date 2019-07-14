boolean resolveIsInfinite(){
  return mOrientationHelper.getMode() == View.MeasureSpec.UNSPECIFIED && mOrientationHelper.getEnd() == 0;
}
