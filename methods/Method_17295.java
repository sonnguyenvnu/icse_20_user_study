/** 
 * Deletes all of the entries using the cache writer, retaining only the keys that succeeded. 
 */
private @Nullable CacheWriterException deleteAllToCacheWriter(Set<? extends K> keys){
  if (!configuration.isWriteThrough() || keys.isEmpty()) {
    return null;
  }
  List<K> keysToDelete=new ArrayList<>(keys);
  try {
    writer.deleteAll(keysToDelete);
    return null;
  }
 catch (  CacheWriterException e) {
    keys.removeAll(keysToDelete);
    throw e;
  }
catch (  RuntimeException e) {
    keys.removeAll(keysToDelete);
    return new CacheWriterException("Exception in CacheWriter",e);
  }
}
