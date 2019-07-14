/** 
 * Unsafe version of  {@link #m_camUp(int) m_camUp}. 
 */
public static float nm_camUp(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_CAMUP + check(index,3) * 4);
}
