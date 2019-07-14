@Override public void requestSmoothFocus(int index,int offset,SmoothScrollAlignmentType type){
  mTarget.requestSmoothFocus(index,offset,type);
  maybeLogRequestFocus(index);
}
