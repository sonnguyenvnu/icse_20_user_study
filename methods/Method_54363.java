/** 
 * Unsafe version of  {@link #m_pixelWidth}. 
 */
public static int nm_pixelWidth(long struct){
  return UNSAFE.getInt(null,struct + B3CameraImageData.M_PIXELWIDTH);
}
