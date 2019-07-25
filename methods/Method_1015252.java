/** 
 * @param length the bit length of the vector
 * @return a bit vector which can hold the specified number of bits
 */
public static long[] create(int length){
  return new long[(Math.max(0,length - 1) >> 6) + 1];
}
