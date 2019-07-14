/** 
 * Applies a hash function to determine the index of the bit.
 * @param hash the seeded hash code
 * @return the mask to the bit
 */
static long bitmask(int hash){
  return 1L << (hash & BITS_PER_LONG_MASK);
}
