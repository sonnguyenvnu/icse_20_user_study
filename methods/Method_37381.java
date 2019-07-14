/** 
 * {@inheritDoc}
 */
@Override public boolean isFull(){
  if (cacheSize == 0) {
    return false;
  }
  return cacheMap.size() >= cacheSize;
}
