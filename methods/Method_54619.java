/** 
 * Unsafe version of  {@link #m_projectionMatrix}. 
 */
public static FloatBuffer nm_projectionMatrix(long struct){
  return memFloatBuffer(struct + B3OpenGLVisualizerCameraInfo.M_PROJECTIONMATRIX,16);
}
