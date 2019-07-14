public int pdeOffsetToTabOffset(int tabIndex,int pdeOffset){
  int tabStartOffset=tabStartOffsets[clipTabIndex(tabIndex)];
  return pdeOffset - tabStartOffset;
}
