/** 
 * Unsafe version of  {@link #b4}. 
 */
public static float nb4(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix4x4.B4);
}
