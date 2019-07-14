/** 
 * Decodes the null-terminated string pointed to by the  {@code value} field. 
 */
@NativeType("char *") public String valueString(){
  return nvalueString(address());
}
