/** 
 * Converts IEEE 754 representation of a double to sortable order (or back to the original) 
 */
public static long sortableDoubleBits(long bits){
  return bits ^ (bits >> 63) & 0x7fffffffffffffffL;
}
