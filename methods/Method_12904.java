/** 
 * Merge the given array into the given Collection.
 * @param array the array to merge (may be {@code null})
 * @param collection the target Collection to merge the array into
 */
@SuppressWarnings("unchecked") public static <E>void mergeArrayIntoCollection(Object array,Collection<E> collection){
  if (collection == null) {
    throw new IllegalArgumentException("Collection must not be null");
  }
  Object[] arr=ObjectUtils.toObjectArray(array);
  for (  Object elem : arr) {
    collection.add((E)elem);
  }
}
