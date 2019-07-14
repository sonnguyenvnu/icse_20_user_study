/** 
 * Unsafe version of  {@link #m_depthValues(int) m_depthValues}. 
 */
public static FloatBuffer nm_depthValues(long struct,int capacity){
  return memFloatBuffer(memGetAddress(struct + B3CameraImageData.M_DEPTHVALUES),capacity);
}
