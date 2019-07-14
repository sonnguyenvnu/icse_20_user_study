/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code fileExtension} field. 
 */
@NativeType("char const *") public ByteBuffer fileExtension(){
  return nfileExtension(address());
}
