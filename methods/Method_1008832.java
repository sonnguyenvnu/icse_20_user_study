/** 
 * Returns a capacity that is sufficient to keep the map from being resized as long as it grows no larger than expectedSize and the load factor is ? its default (0.75).
 */
static int capacity(int expectedSize){
  if (expectedSize < 3) {
    return expectedSize + 1;
  }
  if (expectedSize < Ints.MAX_POWER_OF_TWO) {
    return (int)((float)expectedSize / 0.75F + 1.0F);
  }
  return Integer.MAX_VALUE;
}
