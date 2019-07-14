/** 
 * Unsafe version of  {@link #c2}. 
 */
public static float nc2(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.C2);
}
