/** 
 * ??redis standalone?builder
 * @param appId
 * @return
 */
public static RedisStandaloneBuilder redisStandalone(final long appId){
  return new RedisStandaloneBuilder(appId);
}
