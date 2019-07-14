/** 
 * Decodes the null-terminated string pointed to by the  {@code description} field. 
 */
@NativeType("char const *") public String descriptionString(){
  return ndescriptionString(address());
}
