/** 
 * Returns new array with boolean inserted at the specified index.
 * @param array  array to process
 * @param index  insert index
 * @param object boolean to insert
 * @return new array with boolean inserted at the specified index
 */
public static boolean[] insert(final boolean[] array,final int index,final boolean object){
  final boolean[] newArray=new boolean[array.length + 1];
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
