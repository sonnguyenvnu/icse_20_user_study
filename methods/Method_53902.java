/** 
 * Unsafe version of  {@link #a1}. 
 */
public static float na1(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.A1);
}
