/** 
 * Computes the size of a variable-length encoded long.  Note that this is <em>not</em> currently using variable-length encoding (will be implemented later).
 * @param num the long
 * @return the number of bytes needed to variable-length encode num
 */
public static int size(long num){
  return (byte)(num == 0 ? 1 : bytesRequiredFor(num) + 1);
}
