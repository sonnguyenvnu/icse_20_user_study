/** 
 * Unsafe version of  {@link #m_depthValues(FloatBuffer) m_depthValues}. 
 */
public static void nm_depthValues(long struct,FloatBuffer value){
  memPutAddress(struct + B3CameraImageData.M_DEPTHVALUES,memAddress(value));
}
