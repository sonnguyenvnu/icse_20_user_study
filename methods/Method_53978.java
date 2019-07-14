/** 
 * Unsafe version of  {@link #lights}. 
 */
public static int nlights(long struct){
  return UNSAFE.getInt(null,struct + AIMemoryInfo.LIGHTS);
}
