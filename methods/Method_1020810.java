public RedisServerBuilder setting(String configLine){
  if (redisConf != null) {
    throw new RedisBuildingException("Redis configuration is already set using redis conf file!");
  }
  if (redisConfigBuilder == null) {
    redisConfigBuilder=new StringBuilder();
  }
  redisConfigBuilder.append(configLine);
  redisConfigBuilder.append(LINE_SEPARATOR);
  return this;
}
