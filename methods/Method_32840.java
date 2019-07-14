@Override public float onScale(float scale){
  if (scale < 0.9f || scale > 1.1f) {
    boolean increase=scale > 1.f;
    mActivity.changeFontSize(increase);
    return 1.0f;
  }
  return scale;
}
