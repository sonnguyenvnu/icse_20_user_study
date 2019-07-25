/** 
 * Returns copy of the specified list. Note that this method will copy same list values into the new list.
 * @param collection list to copy
 * @param < T >        list type
 * @return copy of the specified list
 */
public static <T>ArrayList<T> copy(final Collection<T> collection){
  if (collection == null) {
    return null;
  }
  return new ArrayList<T>(collection);
}
