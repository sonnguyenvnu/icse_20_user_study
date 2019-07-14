/** 
 * Unsafe version of  {@link #mClipPlaneNear(float) mClipPlaneNear}. 
 */
public static void nmClipPlaneNear(long struct,float value){
  UNSAFE.putFloat(null,struct + AICamera.MCLIPPLANENEAR,value);
}
