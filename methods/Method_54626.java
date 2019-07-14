/** 
 * Unsafe version of  {@link #m_vertical}. 
 */
public static FloatBuffer nm_vertical(long struct){
  return memFloatBuffer(struct + B3OpenGLVisualizerCameraInfo.M_VERTICAL,3);
}
