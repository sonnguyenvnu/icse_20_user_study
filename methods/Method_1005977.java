/** 
 * By trying to access the session we only trigger a deletion if it the TTL is expired. This is done to handle https://github.com/spring-projects/spring-session/issues/93
 * @param key the key
 */
private void touch(String key){
  this.redis.hasKey(key);
}
