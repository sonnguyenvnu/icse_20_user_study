@Bean public JedisPool JedisPoolFactory(){
  JedisPoolConfig poolConfig=new JedisPoolConfig();
  poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
  poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
  poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
  JedisPool jp=new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout() * 1000,redisConfig.getPassword(),0);
  return jp;
}
