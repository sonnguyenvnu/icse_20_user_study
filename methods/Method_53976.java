/** 
 * Unsafe version of  {@link #animations}. 
 */
public static int nanimations(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.ANIMATIONS);
}
