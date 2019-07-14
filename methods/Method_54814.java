/** 
 * Copies the specified  {@link FloatBuffer} to the {@code m_auxAnalogAxis} field. 
 */
public B3VRControllerEvent m_auxAnalogAxis(@NativeType("float[MAX_VR_ANALOG_AXIS * 2]") FloatBuffer value){
  nm_auxAnalogAxis(address(),value);
  return this;
}
