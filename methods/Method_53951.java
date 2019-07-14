/** 
 * Unsafe version of  {@link #d3}. 
 */
public static float nd3(long struct){
  return UNSAFE.getFloat(null,struct + AIMatrix4x4.D3);
}
