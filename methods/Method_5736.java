private void notifyBytesRead(){
  if (eventListener != null && totalCachedBytesRead > 0) {
    eventListener.onCachedBytesRead(cache.getCacheSpace(),totalCachedBytesRead);
    totalCachedBytesRead=0;
  }
}
