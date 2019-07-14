public synchronized boolean isTreeValidForSizeSpecs(int widthSpec,int heightSpec){
  return isTreeValid() && mLastRequestedWidthSpec == widthSpec && mLastRequestedHeightSpec == heightSpec;
}
