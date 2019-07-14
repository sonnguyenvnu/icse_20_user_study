/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code description} field. 
 */
@NativeType("char const *") public ByteBuffer description(){
  return ndescription(address());
}
