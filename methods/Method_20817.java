/** 
 * Checks the size of a list and returns `true` if the list is non empty.
 */
public static <T>boolean nonEmpty(final @NonNull List<T> xs){
  return xs.size() > 0;
}
