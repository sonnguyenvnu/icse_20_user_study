/** 
 * Unsafe version of  {@link #m_viewMatrix(int) m_viewMatrix}. 
 */
public static float nm_viewMatrix(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_VIEWMATRIX + check(index,16) * 4);
}
