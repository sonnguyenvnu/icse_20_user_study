/** 
 * @param bitLen  the number of significant bits in each vector
 * @param vectors a list of bit-vectors
 * @return the bit-wise interleaving of the values, starting with the topmost bit of the 0th vector, followed by thetopmost bit of the 1st vector, and downward from there
 */
public static long[] interleave(int bitLen,long[] vectors){
  long[] interleaved=create(bitLen * vectors.length);
  int offset=(interleaved.length << 6) - 1;
  for (int i=0; i < bitLen; i++) {
    long mask=1L << i;
    for (int j=vectors.length - 1; j >= 0; j--) {
      long val=(vectors[j] & mask) >>> i;
      interleaved[offset >> 6]|=val << (63 - (offset & 63));
      offset--;
    }
  }
  return interleaved;
}
