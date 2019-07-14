/** 
 * ????
 * @param jedisPoolConfig
 * @return
 */
public RedisClusterBuilder setJedisPoolConfig(GenericObjectPoolConfig jedisPoolConfig){
  this.jedisPoolConfig=jedisPoolConfig;
  return this;
}
