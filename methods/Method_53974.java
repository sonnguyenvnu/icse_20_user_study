/** 
 * Unsafe version of  {@link #meshes}. 
 */
public static int nmeshes(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.MESHES);
}
