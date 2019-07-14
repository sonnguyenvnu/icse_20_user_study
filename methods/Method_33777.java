private void ensureTarget(){
  if (mTargetView == null) {
    mTargetView=getChildAt(getChildCount() - 1);
  }
}
