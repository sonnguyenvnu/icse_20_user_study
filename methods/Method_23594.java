/** 
 * Copy values into the specified array. If the specified array is null or not the same size, a new array will be allocated.
 * @param array
 */
public double[] array(double[] array){
  if (array == null || array.length != count) {
    array=new double[count];
  }
  System.arraycopy(data,0,array,0,count);
  return array;
}
