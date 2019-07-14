/** 
 * Unsafe version of  {@link #m_rgbColorData(ByteBuffer) m_rgbColorData}. 
 */
public static void nm_rgbColorData(long struct,ByteBuffer value){
  memPutAddress(struct + B3CameraImageData.M_RGBCOLORDATA,memAddress(value));
}
