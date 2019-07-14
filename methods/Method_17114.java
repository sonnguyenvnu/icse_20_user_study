/** 
 * Returns a writer that does nothing.
 * @param < K > the type of keys
 * @param < V > the type of values
 * @return a writer that performs no operations
 */
static @NonNull <K,V>CacheWriter<K,V> disabledWriter(){
  @SuppressWarnings("unchecked") CacheWriter<K,V> writer=(CacheWriter<K,V>)DisabledWriter.INSTANCE;
  return writer;
}
