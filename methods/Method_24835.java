public int pdeOffsetToTabIndex(int pdeOffset){
  pdeOffset=Math.max(0,pdeOffset);
  int tab=Arrays.binarySearch(tabStartOffsets,pdeOffset);
  if (tab < 0) {
    tab=-(tab + 1) - 1;
  }
  return tab;
}
