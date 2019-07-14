/** 
 * Find a single value of the given type in the given Collection.
 * @param collection the Collection to search
 * @param type the type to look for
 * @return a value of the given type found if there is a clear match,or  {@code null} if none or more than one such value found
 */
@SuppressWarnings("unchecked") public static <T>T findValueOfType(Collection<?> collection,Class<T> type){
  if (isEmpty(collection)) {
    return null;
  }
  T value=null;
  for (  Object element : collection) {
    if (type == null || type.isInstance(element)) {
      if (value != null) {
        return null;
      }
      value=(T)element;
    }
  }
  return value;
}
