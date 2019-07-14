/** 
 * Sets the address of the specified  {@link IntBuffer} to the {@code m_segmentationMaskValues} field. 
 */
public B3CameraImageData m_segmentationMaskValues(@NativeType("int const *") IntBuffer value){
  nm_segmentationMaskValues(address(),value);
  return this;
}
