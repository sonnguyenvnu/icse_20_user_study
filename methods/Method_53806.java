/** 
 * Unsafe version of  {@link #mHorizontalFOV(float) mHorizontalFOV}. 
 */
public static void nmHorizontalFOV(long struct,float value){
  UNSAFE.putFloat(null,struct + AICamera.MHORIZONTALFOV,value);
}
