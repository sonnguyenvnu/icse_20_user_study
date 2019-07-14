private void notifySpanTouched(SimpleCacheSpan oldSpan,CacheSpan newSpan){
  ArrayList<Listener> keyListeners=listeners.get(oldSpan.key);
  if (keyListeners != null) {
    for (int i=keyListeners.size() - 1; i >= 0; i--) {
      keyListeners.get(i).onSpanTouched(this,oldSpan,newSpan);
    }
  }
  evictor.onSpanTouched(this,oldSpan,newSpan);
}
