/** 
 * Computes the size of a variable-length encoded int
 * @param num the int
 * @return the number of bytes needed to variable-length encode num
 */
public static int size(int num){
  return (byte)(num == 0 ? 1 : bytesRequiredFor(num) + 1);
}
