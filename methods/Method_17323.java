/** 
 * Returns if the value will never expire. 
 */
public boolean isEternal(){
  return (expireTimeMS == Long.MAX_VALUE);
}
