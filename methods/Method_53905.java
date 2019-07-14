/** 
 * Unsafe version of  {@link #b1}. 
 */
public static float nb1(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.B1);
}
