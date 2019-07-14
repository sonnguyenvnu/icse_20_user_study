protected float getDownloadedLengthFromOffset(final float progress){
  ArrayList<Range> ranges=notLoadedBytesRangesCopy;
  if (totalBytesCount == 0 || ranges == null) {
    return 0;
  }
  return progress + getDownloadedLengthFromOffsetInternal(ranges,(int)(totalBytesCount * progress),totalBytesCount) / (float)totalBytesCount;
}
