@Override public void clean(){
  log.debug("  EhcacheService  clean all ");
  Cache eternalCache=manager.getCache(SYSTEM_BASE_CACHE);
  Cache tagCache=manager.getCache(TAG_CACHE);
  if (eternalCache != null) {
    eternalCache.removeAll();
  }
  if (tagCache != null) {
    tagCache.removeAll();
  }
}
