/** 
 * Unsafe version of  {@link #m_vertical(int,float) m_vertical}. 
 */
public static void nm_vertical(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_VERTICAL + check(index,3) * 4,value);
}
