/** 
 * Unsafe version of  {@link #m_height(int) m_height}. 
 */
public static void nm_height(long struct,int value){
  UNSAFE.putInt(null,struct + B3OpenGLVisualizerCameraInfo.M_HEIGHT,value);
}
