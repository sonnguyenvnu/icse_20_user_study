/** 
 * Computes the size of a variable-length encoded double
 * @param num the double
 * @return the number of bytes needed to variable-length encode num
 */
public static int size(double num){
  return size(Double.doubleToLongBits(num));
}
