@Override public void requestSmoothFocus(int index,int offset,SmoothScrollAlignmentType type){
  mRecyclerBinder.scrollSmoothToPosition(index,offset,type);
}
