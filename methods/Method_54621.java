/** 
 * Unsafe version of  {@link #m_camUp}. 
 */
public static FloatBuffer nm_camUp(long struct){
  return memFloatBuffer(struct + B3OpenGLVisualizerCameraInfo.M_CAMUP,3);
}
