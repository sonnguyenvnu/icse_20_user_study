static private int calculateProgressPercent(long totalExpectedSize,long totalRetrievedSize){
  return totalExpectedSize == 0 ? -1 : (int)(totalRetrievedSize * 100 / totalExpectedSize);
}
