private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int newItemTop){
  LazySpanLookup.FullSpanItem fsi=new LazySpanLookup.FullSpanItem();
  fsi.mGapPerSpan=new int[mSpanCount];
  for (int i=0; i < mSpanCount; i++) {
    fsi.mGapPerSpan[i]=newItemTop - mSpans[i].getEndLine(newItemTop);
  }
  return fsi;
}
