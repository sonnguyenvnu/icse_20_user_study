/** 
 * Unsafe version of  {@link #mAspect}. 
 */
public static float nmAspect(long struct){
  return UNSAFE.getFloat(null,struct + AICamera.MASPECT);
}
