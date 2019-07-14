/** 
 * Unsafe version of  {@link #materials}. 
 */
public static int nmaterials(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.MATERIALS);
}
