/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code lpszClassName} field. 
 */
@NativeType("LPCTSTR") public ByteBuffer lpszClassName(){
  return nlpszClassName(address());
}
