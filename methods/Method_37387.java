@Override protected Cache<File,byte[]> createCache(){
  return new LRUCache<File,byte[]>(0,timeout){
    @Override public boolean isFull(){
      return usedSize > FileLRUCache.this.maxSize;
    }
    @Override protected boolean isReallyFull(    final File file){
      return isFull();
    }
    @Override protected void onRemove(    final File key,    final byte[] cachedObject){
      usedSize-=cachedObject.length;
    }
  }
;
}
