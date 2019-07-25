/** 
 * Returns new array with the object under specified index removed.
 * @param array array to process
 * @param index index of object to remove
 * @param < T >   component type
 * @return new array with the object under specified index removed
 */
public static <T>T[] remove(final T[] array,final int index){
  final T[] newArray=createArray(array,array.length - 1);
  int mod=0;
  for (int i=0; i < array.length; i++) {
    if (i == index) {
      mod=-1;
    }
 else {
      newArray[i + mod]=array[i];
    }
  }
  return newArray;
}
