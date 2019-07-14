/** 
 * Returns an integer X such that 2^X=value. Throws an exception if value is not a power of 2.
 * @param value
 * @return
 */
public static int getPowerOf2(long value){
  Preconditions.checkArgument(isPowerOf2(value));
  return Long.SIZE - (Long.numberOfLeadingZeros(value) + 1);
}
