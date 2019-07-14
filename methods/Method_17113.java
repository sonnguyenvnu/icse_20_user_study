/** 
 * Returns a map of the values associated with the  {@code keys}, creating or retrieving those values if necessary. The returned map contains entries that were already cached, combined with the newly loaded entries; it will never contain null keys or values. <p> A single request to the  {@code mappingFunction} is performed for all keys which are not alreadypresent in the cache. All entries returned by  {@code mappingFunction} will be stored in thecache, over-writing any previously cached values. If another call to  {@link #get} tries to loadthe value for a key in  {@code keys}, implementations may either have that thread load the entry or simply wait for this thread to finish and returns the loaded value. In the case of overlapping non-blocking loads, the last load to complete will replace the existing entry. Note that multiple threads can concurrently load values for distinct keys. <p> Note that duplicate elements in  {@code keys}, as determined by  {@link Object#equals}, will be ignored.
 * @param keys the keys whose associated values are to be returned
 * @param mappingFunction the function to compute the values
 * @return an unmodifiable mapping of keys to values for the specified keys in this cache
 * @throws NullPointerException if the specified collection is null or contains a null element, orif the mao returned by the mappingFunction is null
 * @throws RuntimeException or Error if the mappingFunction does so, in which case the mapping isleft unestablished
 */
@NonNull default Map<K,V> getAll(@NonNull Iterable<? extends @NonNull K> keys,@NonNull Function<Iterable<? extends @NonNull K>,@NonNull Map<K,V>> mappingFunction){
  throw new UnsupportedOperationException();
}
