@Override public void clean(String cacheName){
  log.info("  EhcacheService  clean cacheName?[{}] ",cacheName);
  Cache eCache=manager.getCache(cacheName);
  if (eCache != null) {
    eCache.removeAll();
  }
}
