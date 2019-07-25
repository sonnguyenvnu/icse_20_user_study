/** 
 * Determine if a key/value pair with this key exists in the Object.
 * @param key The key to check
 * @return True if the key exists, false otherwise.
 */
public boolean contains(final String key){
  v8.checkThread();
  checkReleased();
  checkKey(key);
  return v8.contains(v8.getV8RuntimePtr(),objectHandle,key);
}
