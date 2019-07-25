/** 
 * Create an immutable array.
 * @param array the data
 * @return the new immutable array
 */
@SafeVarargs public static <K>ImmutableArray<K> create(K... array){
  return new ImmutableArray<>(array);
}
