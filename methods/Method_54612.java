/** 
 * Returns a  {@link FloatBuffer} view of the {@code m_camForward} field. 
 */
@NativeType("float[3]") public FloatBuffer m_camForward(){
  return nm_camForward(address());
}
