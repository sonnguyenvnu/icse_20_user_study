/** 
 * Unsafe version of  {@link #a3}. 
 */
public static float na3(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.A3);
}
