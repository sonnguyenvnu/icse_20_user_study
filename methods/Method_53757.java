/** 
 * Unsafe version of  {@link #mDuration}. 
 */
public static double nmDuration(long struct){
  return UNSAFE.getDouble(null,struct + AIAnimation.MDURATION);
}
