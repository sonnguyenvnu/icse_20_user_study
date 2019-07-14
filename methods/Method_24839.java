public int tabOffsetToTabLine(int tabIndex,int tabOffset){
  int tabStartOffset=tabStartOffsets[clipTabIndex(tabIndex)];
  return offsetToLine(pdeCode,tabStartOffset,tabStartOffset + tabOffset);
}
