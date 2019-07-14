/** 
 * Decodes the null-terminated string pointed to by the  {@code mComments} field. 
 */
@NativeType("char const *") public String mCommentsString(){
  return nmCommentsString(address());
}
