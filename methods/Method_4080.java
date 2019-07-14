private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int newItemBottom){
  LazySpanLookup.FullSpanItem fsi=new LazySpanLookup.FullSpanItem();
  fsi.mGapPerSpan=new int[mSpanCount];
  for (int i=0; i < mSpanCount; i++) {
    fsi.mGapPerSpan[i]=mSpans[i].getStartLine(newItemBottom) - newItemBottom;
  }
  return fsi;
}
