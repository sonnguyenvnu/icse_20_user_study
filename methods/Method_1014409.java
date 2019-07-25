/** 
 * Removes the file associated with the given key from the cache.
 * @param key the key whose associated file is to be removed
 */
public void remove(String key){
  deleteFile(getUniqueFile(key));
}
