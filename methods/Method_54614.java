/** 
 * Copies the specified  {@link FloatBuffer} to the {@code m_camUp} field. 
 */
public B3OpenGLVisualizerCameraInfo m_camUp(@NativeType("float[3]") FloatBuffer value){
  nm_camUp(address(),value);
  return this;
}
