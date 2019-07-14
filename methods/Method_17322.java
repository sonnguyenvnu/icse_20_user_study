/** 
 * Returns if the value has expired and is eligible for eviction. 
 */
public boolean hasExpired(long currentTimeMS){
  return (currentTimeMS - expireTimeMS) >= 0;
}
