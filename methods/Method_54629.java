/** 
 * Unsafe version of  {@link #m_viewMatrix(int,float) m_viewMatrix}. 
 */
public static void nm_viewMatrix(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_VIEWMATRIX + check(index,16) * 4,value);
}
