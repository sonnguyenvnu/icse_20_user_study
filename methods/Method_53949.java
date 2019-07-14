/** 
 * Unsafe version of  {@link #b3}. 
 */
public static float nb3(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix4x4.B3);
}
