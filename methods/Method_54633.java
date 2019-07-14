/** 
 * Unsafe version of  {@link #m_camUp(int,float) m_camUp}. 
 */
public static void nm_camUp(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_CAMUP + check(index,3) * 4,value);
}
