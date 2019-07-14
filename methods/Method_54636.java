/** 
 * Unsafe version of  {@link #m_horizontal(FloatBuffer) m_horizontal}. 
 */
public static void nm_horizontal(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_HORIZONTAL,value.remaining() * 4);
}
