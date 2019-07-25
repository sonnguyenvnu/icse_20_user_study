/** 
 * Create an immutable array.
 * @param array the data
 * @return the new immutable array
 */
@SafeVarargs public static <K>ImmutableArray2<K> create(K... array){
  return new ImmutableArray2<>(array,array.length);
}
