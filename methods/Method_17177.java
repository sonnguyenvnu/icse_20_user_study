/** 
 * This method assumes index is actually (index << 1) because lower bit is used for resize. This is compensated for by reducing the element shift. The computation is constant folded, so there's no cost.
 */
private static long modifiedCalcElementOffset(long index,long mask){
  return REF_ARRAY_BASE + ((index & mask) << (REF_ELEMENT_SHIFT - 1));
}
