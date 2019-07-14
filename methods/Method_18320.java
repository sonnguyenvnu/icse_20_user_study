@Override public void offsetTopAndBottom(int offset){
  super.offsetTopAndBottom(offset);
  maybePerformIncrementalMountOnView();
}
