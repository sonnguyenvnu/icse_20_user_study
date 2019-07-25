@Override public int lock(String s,FuseFileInfo fuseFileInfo,int i,Flock flock){
  try {
    if (DEBUG)     System.out.printf("lock(%s)\n",s);
    CacheEntryHolder cacheEntryHolder=entryMap.get(s);
    if (cacheEntryHolder != null)     cacheEntryHolder.syncAndClear();
    return 0;
  }
 catch (  Throwable t) {
    LOG.log(Level.WARNING,t.getMessage(),t);
    throw t;
  }
}
