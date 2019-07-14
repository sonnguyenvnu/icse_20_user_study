@Override public void offsetLeftAndRight(int offset){
  super.offsetLeftAndRight(offset);
  maybePerformIncrementalMountOnView();
}
