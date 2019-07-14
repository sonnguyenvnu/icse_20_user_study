/** 
 * Unsafe version of  {@link #total}. 
 */
public static int ntotal(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.TOTAL);
}
