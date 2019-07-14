/** 
 * Copies the specified  {@link FloatBuffer} to the {@code m_camForward} field. 
 */
public B3OpenGLVisualizerCameraInfo m_camForward(@NativeType("float[3]") FloatBuffer value){
  nm_camForward(address(),value);
  return this;
}
