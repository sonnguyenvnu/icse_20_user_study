/** 
 * Unsafe version of  {@link #m_camForward(int) m_camForward}. 
 */
public static float nm_camForward(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_CAMFORWARD + check(index,3) * 4);
}
