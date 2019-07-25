/** 
 * @param vector the bit vector
 * @param bitIndex the bit to be tested
 * @return true if the bit is 1, false otherwise
 */
public static boolean test(long[] vector,int bitIndex){
  return (vector[bitIndex >> 6] & (1L << (bitIndex & 63))) != 0;
}
