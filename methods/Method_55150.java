/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code types} field. 
 */
@NativeType("char *") public ByteBuffer types(){
  return ntypes(address());
}
