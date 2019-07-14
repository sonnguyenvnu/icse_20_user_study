/** 
 * Unsafe version of  {@link #m_camForward(int,float) m_camForward}. 
 */
public static void nm_camForward(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_CAMFORWARD + check(index,3) * 4,value);
}
