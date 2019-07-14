/** 
 * Unsafe version of  {@link #mAspect(float) mAspect}. 
 */
public static void nmAspect(long struct,float value){
  UNSAFE.putFloat(null,struct + AICamera.MASPECT,value);
}
