/** 
 * Unsafe version of  {@link #m_viewMatrix}. 
 */
public static FloatBuffer nm_viewMatrix(long struct){
  return memFloatBuffer(struct + B3OpenGLVisualizerCameraInfo.M_VIEWMATRIX,16);
}
