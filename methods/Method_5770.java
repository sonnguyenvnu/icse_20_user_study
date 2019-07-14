@Override public void onSpanTouched(Cache cache,CacheSpan oldSpan,CacheSpan newSpan){
  onSpanRemoved(cache,oldSpan);
  onSpanAdded(cache,newSpan);
}
