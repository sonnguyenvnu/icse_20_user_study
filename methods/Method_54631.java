/** 
 * Unsafe version of  {@link #m_projectionMatrix(int,float) m_projectionMatrix}. 
 */
public static void nm_projectionMatrix(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_PROJECTIONMATRIX + check(index,16) * 4,value);
}
