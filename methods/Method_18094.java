@Override public int getChildIndex(InternalNode child){
  for (int i=0, count=mYogaNode.getChildCount(); i < count; i++) {
    if (mYogaNode.getChildAt(i) == child.getYogaNode()) {
      return i;
    }
  }
  return -1;
}
