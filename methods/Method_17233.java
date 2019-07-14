/** 
 * Returns the closest power-of-two at or higher than the given value. 
 */
static int ceilingNextPowerOfTwo(int x){
  return 1 << (Integer.SIZE - Integer.numberOfLeadingZeros(x - 1));
}
