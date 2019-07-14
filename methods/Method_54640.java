/** 
 * Unsafe version of  {@link #m_yaw(float) m_yaw}. 
 */
public static void nm_yaw(long struct,float value){
  UNSAFE.putFloat(null,struct + B3OpenGLVisualizerCameraInfo.M_YAW,value);
}
