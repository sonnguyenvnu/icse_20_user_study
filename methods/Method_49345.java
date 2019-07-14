/** 
 * Converts IEEE 754 representation of a float to sortable order (or back to the original) 
 */
public static int sortableFloatBits(int bits){
  return bits ^ (bits >> 31) & 0x7fffffff;
}
