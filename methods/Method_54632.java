/** 
 * Unsafe version of  {@link #m_camUp(FloatBuffer) m_camUp}. 
 */
public static void nm_camUp(long struct,FloatBuffer value){
  if (CHECKS) {
    checkGT(value,3);
  }
  memCopy(memAddress(value),struct + B3OpenGLVisualizerCameraInfo.M_CAMUP,value.remaining() * 4);
}
