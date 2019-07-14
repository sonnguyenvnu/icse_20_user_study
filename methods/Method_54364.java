/** 
 * Unsafe version of  {@link #m_pixelHeight}. 
 */
public static int nm_pixelHeight(long struct){
  return UNSAFE.getInt(null,struct + B3CameraImageData.M_PIXELHEIGHT);
}
