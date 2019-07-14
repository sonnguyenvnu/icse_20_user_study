/** 
 * Unsafe version of  {@link #m_vertical(FloatBuffer) m_vertical}. 
 */
public static void nm_vertical(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_VERTICAL,value.remaining() * 4);
}
