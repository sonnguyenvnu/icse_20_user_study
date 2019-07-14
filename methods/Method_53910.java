/** 
 * Unsafe version of  {@link #c3}. 
 */
public static float nc3(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix3x3.C3);
}
