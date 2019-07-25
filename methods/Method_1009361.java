/** 
 * Creates a iterator from given iterable.
 * @param iterable the iterable
 * @return a iterator
 */
@SuppressWarnings("unchecked") public static Iterator<Object> iterator(Object iterable){
  Assert.state(isIterable(iterable.getClass()));
  return iterable.getClass().isArray() ? new ArrayIterator(iterable) : ((Iterable<Object>)iterable).iterator();
}
