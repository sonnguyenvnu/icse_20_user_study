@Override public float getCacheMissPercentage(){
  long requestCount=getCacheGets();
  return (requestCount == 0) ? 0f : 100 * ((float)getCacheMisses() / requestCount);
}
