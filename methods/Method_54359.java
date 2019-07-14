/** 
 * Returns a  {@link FloatBuffer} view of the data pointed to by the {@code m_depthValues} field.
 * @param capacity the number of elements in the returned buffer
 */
@NativeType("float const *") public FloatBuffer m_depthValues(int capacity){
  return nm_depthValues(address(),capacity);
}
