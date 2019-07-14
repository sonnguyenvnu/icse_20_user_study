/** 
 * Unsafe version of  {@link #mHorizontalFOV}. 
 */
public static float nmHorizontalFOV(long struct){
  return UNSAFE.getFloat(null,struct + AICamera.MHORIZONTALFOV);
}
