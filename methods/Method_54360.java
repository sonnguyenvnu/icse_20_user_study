/** 
 * Returns a  {@link IntBuffer} view of the data pointed to by the {@code m_segmentationMaskValues} field.
 * @param capacity the number of elements in the returned buffer
 */
@NativeType("int const *") public IntBuffer m_segmentationMaskValues(int capacity){
  return nm_segmentationMaskValues(address(),capacity);
}
