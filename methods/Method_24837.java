public int tabOffsetToJavaOffset(int tabIndex,int tabOffset){
  int tabStartOffset=tabStartOffsets[clipTabIndex(tabIndex)];
  int pdeOffset=tabStartOffset + tabOffset;
  return offsetMapper.getOutputOffset(pdeOffset);
}
