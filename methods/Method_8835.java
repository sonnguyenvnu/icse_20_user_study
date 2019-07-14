@Override public boolean onScaleBegin(ScaleGestureDetector detector){
  previousScale=1.0f;
  hasTransformed=true;
  return true;
}
