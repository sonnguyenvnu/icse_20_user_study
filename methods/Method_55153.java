/** 
 * Decodes the null-terminated string pointed to by the  {@code name} field. 
 */
@NativeType("char *") public String nameString(){
  return nnameString(address());
}
