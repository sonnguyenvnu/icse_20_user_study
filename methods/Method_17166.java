/** 
 * See  {@link Cache#invalidateAll(Iterable)}. 
 */
default void invalidateAll(Iterable<?> keys){
  for (  Object key : keys) {
    remove(key);
  }
}
