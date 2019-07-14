/** 
 * Select singleton executor.
 * @param key the key
 * @return the singleton executor
 */
public SingletonExecutor select(final String key){
  byte[] digest=md5(key);
  return selectForKey(hash(digest,0));
}
