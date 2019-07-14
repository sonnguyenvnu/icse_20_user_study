/** 
 * Sets the address of the specified  {@link FloatBuffer} to the {@code m_depthValues} field. 
 */
public B3CameraImageData m_depthValues(@NativeType("float const *") FloatBuffer value){
  nm_depthValues(address(),value);
  return this;
}
