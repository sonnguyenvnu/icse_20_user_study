/** 
 * Unsafe version of  {@link #m_camForward(FloatBuffer) m_camForward}. 
 */
public static void nm_camForward(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_CAMFORWARD,value.remaining() * 4);
}
