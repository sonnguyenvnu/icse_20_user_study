/** 
 * Copy values into the specified array. If the specified array is null or not the same size, a new array will be allocated.
 * @param array
 */
public int[] array(int[] array){
  if (array == null || array.length != count) {
    array=new int[count];
  }
  System.arraycopy(data,0,array,0,count);
  return array;
}
