/** 
 * Unsafe version of  {@link #c1}. 
 */
public static float nc1(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.C1);
}
