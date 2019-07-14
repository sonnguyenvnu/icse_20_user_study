/** 
 * Unsafe version of  {@link #m_viewMatrix(FloatBuffer) m_viewMatrix}. 
 */
public static void nm_viewMatrix(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,16);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_VIEWMATRIX,value.remaining() * 4);
}
