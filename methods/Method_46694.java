@Bean @ConditionalOnMissingBean public FastStorageProvider fastStorageProvider(RedisTemplate<String,Object> redisTemplate,StringRedisTemplate stringRedisTemplate,TxManagerConfig managerConfig){
  return () -> new RedisStorage(redisTemplate,stringRedisTemplate,managerConfig);
}
