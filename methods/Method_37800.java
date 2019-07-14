/** 
 * Creates binary search wrapper over an array with given comparator.
 */
public static <T>BinarySearch<T> forArray(final T[] array,final Comparator<T> comparator){
  return new BinarySearch<T>(){
    @Override @SuppressWarnings({"unchecked"}) protected int compare(    final int index,    final T element){
      return comparator.compare(array[index],element);
    }
    @Override protected int getLastIndex(){
      return array.length - 1;
    }
  }
;
}
