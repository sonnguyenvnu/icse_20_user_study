/** 
 * @param index desirable element index
 * @return the offset in bytes within the array for a given index.
 */
public static long calcElementOffset(long index){
  return REF_ARRAY_BASE + (index << REF_ELEMENT_SHIFT);
}
