@Override public long getTimeUs(long position){
  long positionOffset=position - dataStartPosition;
  if (!isSeekable() || positionOffset <= xingFrameSize) {
    return 0L;
  }
  long[] tableOfContents=Assertions.checkNotNull(this.tableOfContents);
  double scaledPosition=(positionOffset * 256d) / dataSize;
  int prevTableIndex=Util.binarySearchFloor(tableOfContents,(long)scaledPosition,true,true);
  long prevTimeUs=getTimeUsForTableIndex(prevTableIndex);
  long prevScaledPosition=tableOfContents[prevTableIndex];
  long nextTimeUs=getTimeUsForTableIndex(prevTableIndex + 1);
  long nextScaledPosition=prevTableIndex == 99 ? 256 : tableOfContents[prevTableIndex + 1];
  double interpolateFraction=prevScaledPosition == nextScaledPosition ? 0 : ((scaledPosition - prevScaledPosition) / (nextScaledPosition - prevScaledPosition));
  return prevTimeUs + Math.round(interpolateFraction * (nextTimeUs - prevTimeUs));
}
