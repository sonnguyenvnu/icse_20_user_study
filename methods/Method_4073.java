@Override public void offsetChildrenHorizontal(int dx){
  super.offsetChildrenHorizontal(dx);
  for (int i=0; i < mSpanCount; i++) {
    mSpans[i].onOffset(dx);
  }
}
