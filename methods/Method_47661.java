private void updateDataOffset(){
  int newDataOffset=scroller.getCurrX() / scrollerBucketSize;
  newDataOffset=Math.max(0,newDataOffset);
  newDataOffset=Math.min(maxDataOffset,newDataOffset);
  if (newDataOffset != dataOffset) {
    dataOffset=newDataOffset;
    scrollController.onDataOffsetChanged(dataOffset);
    postInvalidate();
  }
}
