@Override public synchronized long getCachedLength(String key,long position,long length){
  Assertions.checkState(!released);
  CachedContent cachedContent=index.get(key);
  return cachedContent != null ? cachedContent.getCachedBytesLength(position,length) : -length;
}
