/** 
 * Unsafe version of  {@link #m_horizontal(int,float) m_horizontal}. 
 */
public static void nm_horizontal(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_HORIZONTAL + check(index,3) * 4,value);
}
