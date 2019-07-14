/** 
 * Fill an already-allocated array with the values (more efficient than creating a new array each time). If 'array' is null, or not the same size as the number of values, a new array will be allocated and returned.
 */
public float[] valueArray(float[] array){
  if (array == null || array.length != size()) {
    array=new float[count];
  }
  System.arraycopy(values,0,array,0,count);
  return array;
}
