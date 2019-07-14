@Override public synchronized NavigableSet<CacheSpan> addListener(String key,Listener listener){
  Assertions.checkState(!released);
  ArrayList<Listener> listenersForKey=listeners.get(key);
  if (listenersForKey == null) {
    listenersForKey=new ArrayList<>();
    listeners.put(key,listenersForKey);
  }
  listenersForKey.add(listener);
  return getCachedSpans(key);
}
