private static boolean newSizeIsExactAndMatchesOldMeasuredSize(int newSizeSpecMode,int newSizeSpecSize,float oldMeasuredSize){
  return (newSizeSpecMode == EXACTLY) && (Math.abs(newSizeSpecSize - oldMeasuredSize) < DELTA);
}
