/** 
 * Creates binary search wrapper over a list with given comparator.
 */
public static <T>BinarySearch<T> forList(final List<T> list,final Comparator<T> comparator){
  return new BinarySearch<T>(){
    @Override @SuppressWarnings({"unchecked"}) protected int compare(    final int index,    final T element){
      return comparator.compare(list.get(index),element);
    }
    @Override protected int getLastIndex(){
      return list.size() - 1;
    }
  }
;
}
