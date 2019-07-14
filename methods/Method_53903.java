/** 
 * Unsafe version of  {@link #a2}. 
 */
public static float na2(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.A2);
}
