@Override public void markLayoutSeen(){
  if (arr != null) {
    arr[LAYOUT_EDGE_SET_FLAG_INDEX]=((int)arr[LAYOUT_EDGE_SET_FLAG_INDEX]) & ~(HAS_NEW_LAYOUT);
  }
  mHasNewLayout=false;
}
