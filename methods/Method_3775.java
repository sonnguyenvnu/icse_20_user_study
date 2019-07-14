/** 
 * @param cachedBorders The out array
 * @param spanCount number of spans
 * @param totalSpace total available space after padding is removed
 * @return The updated array. Might be the same instance as the provided array if its sizehas not changed.
 */
protected int[] calculateItemBorders(int[] cachedBorders,int spanCount,int totalSpace){
  if (cachedBorders == null || cachedBorders.length != spanCount + 1 || cachedBorders[cachedBorders.length - 1] != totalSpace) {
    cachedBorders=new int[spanCount + 1];
  }
  cachedBorders[0]=0;
  int sizePerSpan=totalSpace / spanCount;
  int sizePerSpanRemainder=totalSpace % spanCount;
  int consumedPixels=0;
  int additionalSize=0;
  for (int i=1; i <= spanCount; i++) {
    int itemSize=sizePerSpan;
    additionalSize+=sizePerSpanRemainder;
    if (additionalSize > 0 && (spanCount - additionalSize) < sizePerSpanRemainder) {
      itemSize+=1;
      additionalSize-=spanCount;
    }
    consumedPixels+=itemSize;
    cachedBorders[i]=consumedPixels;
  }
  return cachedBorders;
}
