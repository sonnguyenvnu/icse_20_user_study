/** 
 * Gives a random element of the given collection.
 * @param < T > type of items in the collection
 * @param items a collection
 * @return a randomly chosen element from the collection
 */
@SuppressWarnings("unchecked") public <T>T choose(Collection<T> items){
  Object[] array=items.toArray(new Object[items.size()]);
  return (T)array[nextInt(array.length)];
}
