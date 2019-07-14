/** 
 * Returns a  {@link ByteBuffer} view of the null-terminated string pointed to by the {@code mComments} field. 
 */
@NativeType("char const *") public ByteBuffer mComments(){
  return nmComments(address());
}
