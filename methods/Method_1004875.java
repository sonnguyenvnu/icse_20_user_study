/** 
 * Get the size of the cache.
 * @return the number of entries in the caches
 */
default int size(){
  return getAllKeys().size();
}
