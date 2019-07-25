/** 
 * Create an immutable array.
 * @param array the data
 * @return the new immutable array
 */
@SafeVarargs public static <K>ImmutableArray3<K> create(K... array){
  return new Plain<>(array);
}
