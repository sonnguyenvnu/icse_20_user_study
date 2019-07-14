/** 
 * Returns a  {@link ByteBuffer} view of the data pointed to by the {@code m_rgbColorData} field.
 * @param capacity the number of elements in the returned buffer
 */
@NativeType("unsigned char const *") public ByteBuffer m_rgbColorData(int capacity){
  return nm_rgbColorData(address(),capacity);
}
