/** 
 * Unsafe version of  {@link #mDuration(double) mDuration}. 
 */
public static void nmDuration(long struct,double value){
  UNSAFE.putDouble(null,struct + AIAnimation.MDURATION,value);
}
