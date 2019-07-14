/** 
 * Returns the first valid option from this  {@code Choice}. 
 */
public Optional<T> first(){
  Iterator<T> itr=iterator();
  return itr.hasNext() ? Optional.of(itr.next()) : Optional.<T>absent();
}
