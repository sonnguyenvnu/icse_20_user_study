@Override public void onGestureBegin(TransformGestureDetector detector){
  FLog.v(TAG,"onGestureBegin");
  mPreviousTransform.set(mActiveTransform);
  onTransformBegin();
  mWasTransformCorrected=!canScrollInAllDirection();
}
