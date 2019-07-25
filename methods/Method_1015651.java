/** 
 * Computes the size of a variable-length encoded float
 * @param num the float
 * @return the number of bytes needed to variable-length encode num
 */
public static int size(float num){
  return size(Float.floatToIntBits(num));
}
