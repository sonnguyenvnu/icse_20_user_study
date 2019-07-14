/** 
 * Unsafe version of  {@link #m_projectionMatrix(int) m_projectionMatrix}. 
 */
public static float nm_projectionMatrix(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_PROJECTIONMATRIX + check(index,16) * 4);
}
