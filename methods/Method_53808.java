/** 
 * Unsafe version of  {@link #mClipPlaneFar(float) mClipPlaneFar}. 
 */
public static void nmClipPlaneFar(long struct,float value){
  UNSAFE.putFloat(null,struct + AICamera.MCLIPPLANEFAR,value);
}
