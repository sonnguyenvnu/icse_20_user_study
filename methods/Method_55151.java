/** 
 * Decodes the null-terminated string pointed to by the  {@code types} field. 
 */
@NativeType("char *") public String typesString(){
  return ntypesString(address());
}
