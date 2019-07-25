/** 
 * Align a value to the next multiple up of alignment. If the value equals an alignment multiple then it is returned unchanged.
 * @param value to be aligned up.
 * @param alignment to be used, must be a power of 2.
 * @return the value aligned to the next boundary.
 */
public static long align(final long value,final int alignment){
  if (!isPowerOfTwo(alignment)) {
    throw new IllegalArgumentException("alignment must be a power of 2:" + alignment);
  }
  return (value + (alignment - 1)) & ~(alignment - 1);
}
