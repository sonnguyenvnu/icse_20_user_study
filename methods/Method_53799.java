/** 
 * Unsafe version of  {@link #mClipPlaneNear}. 
 */
public static float nmClipPlaneNear(long struct){
  return UNSAFE.getFloat(null,struct + AICamera.MCLIPPLANENEAR);
}
