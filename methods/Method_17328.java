@Override public float getCacheHitPercentage(){
  long requestCount=getCacheGets();
  return (requestCount == 0) ? 0f : 100 * ((float)getCacheHits() / requestCount);
}
