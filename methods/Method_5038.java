@Override public float getDownloadPercentage(){
  long contentLength=cachingCounters.contentLength;
  return contentLength == C.LENGTH_UNSET ? C.PERCENTAGE_UNSET : ((cachingCounters.totalCachedBytes() * 100f) / contentLength);
}
