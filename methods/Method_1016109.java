/** 
 * Returns new array with object inserted at the specified index.
 * @param array  array to process
 * @param index  insert index
 * @param object object to insert
 * @param < T >    component type
 * @return new array with object inserted at the specified index
 */
public static <T>T[] insert(final T[] array,final int index,final T object){
  final T[] newArray=createArray(array,array.length + 1);
  int mod=0;
  for (int i=0; i < array.length; i++) {
    if (i == index) {
      newArray[i]=object;
      mod=1;
    }
    newArray[i + mod]=array[i];
  }
  return newArray;
}
