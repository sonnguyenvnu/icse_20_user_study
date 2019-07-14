/** 
 * Unsafe version of  {@link #mTicksPerSecond(double) mTicksPerSecond}. 
 */
public static void nmTicksPerSecond(long struct,double value){
  UNSAFE.putDouble(null,struct + AIAnimation.MTICKSPERSECOND,value);
}
