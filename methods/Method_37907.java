/** 
 * Returns a collection containing all elements of the iterator.
 */
public static <T>Collection<T> collectionOf(final Iterator<? extends T> iterator){
  final List<T> list=new ArrayList<>();
  while (iterator.hasNext()) {
    list.add(iterator.next());
  }
  return list;
}
