/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code signature} field. 
 */
@NativeType("char *") public ByteBuffer signature(){
  return nsignature(address());
}
