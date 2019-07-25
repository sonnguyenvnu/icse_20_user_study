@Override public void clean(String cacheName){
  log.info("  RedisCacheService  clean cacheName?[{}] ",cacheName);
  Set dictKeys=redisTemplate.keys(cacheName + "*");
  if (dictKeys != null && !dictKeys.isEmpty())   redisTemplate.delete(dictKeys);
}
