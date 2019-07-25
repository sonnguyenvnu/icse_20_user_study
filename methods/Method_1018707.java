/** 
 * @param array array
 * @return a new array containing specified array's elements in reverse order.
 */
public static byte[] reverse(byte[] array){
  byte[] rarray=new byte[array.length];
  for (int i=0; i < array.length; i++) {
    rarray[i]=array[array.length - i - 1];
  }
  return rarray;
}
