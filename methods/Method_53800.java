/** 
 * Unsafe version of  {@link #mClipPlaneFar}. 
 */
public static float nmClipPlaneFar(long struct){
  return UNSAFE.getFloat(null,struct + AICamera.MCLIPPLANEFAR);
}
