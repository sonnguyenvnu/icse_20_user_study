/** 
 * Removes the eldest entry if current cache size exceed cache size.
 */
protected boolean removeEldestEntry(final int currentSize){
  if (cacheSize == 0) {
    return false;
  }
  return currentSize > cacheSize;
}
