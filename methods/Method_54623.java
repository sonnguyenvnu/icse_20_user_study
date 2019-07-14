/** 
 * Unsafe version of  {@link #m_camForward}. 
 */
public static FloatBuffer nm_camForward(long struct){
  return memFloatBuffer(struct + B3OpenGLVisualizerCameraInfo.M_CAMFORWARD,3);
}
