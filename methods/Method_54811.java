/** 
 * Returns a  {@link FloatBuffer} view of the {@code m_auxAnalogAxis} field. 
 */
@NativeType("float[MAX_VR_ANALOG_AXIS * 2]") public FloatBuffer m_auxAnalogAxis(){
  return nm_auxAnalogAxis(address());
}
