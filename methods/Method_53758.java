/** 
 * Unsafe version of  {@link #mTicksPerSecond}. 
 */
public static double nmTicksPerSecond(long struct){
  return UNSAFE.getDouble(null,struct + AIAnimation.MTICKSPERSECOND);
}
