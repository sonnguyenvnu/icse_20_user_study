@Override public long getCacheGets(){
  return getCacheHits() + getCacheMisses();
}
