/** 
 * Constructs a new, empty bimap with the specified expected size.
 * @param expectedSize the expected number of entries
 * @throws IllegalArgumentException if the specified expected size is negative
 */
public static <K,V>HashBiMap<K,V> create(int expectedSize){
  return new HashBiMap<>(expectedSize);
}
