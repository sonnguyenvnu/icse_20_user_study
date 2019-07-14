/** 
 * Unsafe version of  {@link #m_horizontal(int) m_horizontal}. 
 */
public static float nm_horizontal(long struct,int index){
  return UNSAFE.getFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_HORIZONTAL + check(index,3) * 4);
}
