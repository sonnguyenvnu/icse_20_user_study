/** 
 * Unsafe version of  {@link #m_pixelHeight(int) m_pixelHeight}. 
 */
public static void nm_pixelHeight(long struct,int value){
  UNSAFE.putInt(null,struct + B3CameraImageData.M_PIXELHEIGHT,value);
}
