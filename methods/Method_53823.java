/** 
 * Decodes the null-terminated string pointed to by the  {@code fileExtension} field. 
 */
@NativeType("char const *") public String fileExtensionString(){
  return nfileExtensionString(address());
}
