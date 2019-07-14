private static boolean oldSizeIsUnspecifiedAndStillFits(int oldSizeSpecMode,int newSizeSpecMode,int newSizeSpecSize,float oldMeasuredSize){
  return newSizeSpecMode == AT_MOST && oldSizeSpecMode == UNSPECIFIED && newSizeSpecSize >= oldMeasuredSize;
}
