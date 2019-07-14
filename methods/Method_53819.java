/** 
 * Decodes the null-terminated string pointed to by the  {@code id} field. 
 */
@NativeType("char const *") public String idString(){
  return nidString(address());
}
