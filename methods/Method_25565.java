/** 
 * Find the element in the iterable following the target
 * @param target is the element to search for
 * @param iterable is the iterable to search
 * @param defaultValue will be returned if there is no item following the searched for item
 * @return the item following {@code target} or {@code defaultValue} if not found
 */
private static <T>T after(T target,Iterable<? extends T> iterable,T defaultValue){
  Iterator<? extends T> iterator=iterable.iterator();
  while (iterator.hasNext()) {
    if (iterator.next().equals(target)) {
      break;
    }
  }
  if (iterator.hasNext()) {
    return iterator.next();
  }
  return defaultValue;
}
