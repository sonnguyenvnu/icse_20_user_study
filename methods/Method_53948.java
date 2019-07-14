/** 
 * Unsafe version of  {@link #b2}. 
 */
public static float nb2(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix4x4.B2);
}
