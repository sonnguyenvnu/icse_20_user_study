/** 
 * Unsafe version of  {@link #m_projectionMatrix(FloatBuffer) m_projectionMatrix}. 
 */
public static void nm_projectionMatrix(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,16);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_PROJECTIONMATRIX,value.remaining() * 4);
}
