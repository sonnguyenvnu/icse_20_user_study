/** 
 * Returns the expiration time for the entry after being created.
 * @param key the key of the entry that was created
 * @param value the value of the entry that was created
 * @param expiry the calculator for the expiration time
 * @param now the current time, in nanoseconds
 * @return the expiration time
 */
long expireAfterCreate(@Nullable K key,@Nullable V value,Expiry<K,V> expiry,long now){
  if (expiresVariable() && (key != null) && (value != null)) {
    long duration=expiry.expireAfterCreate(key,value,now);
    return isAsync ? (now + duration) : (now + Math.min(duration,MAXIMUM_EXPIRY));
  }
  return 0L;
}
