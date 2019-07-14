/** 
 * Decodes the null-terminated string pointed to by the  {@code signature} field. 
 */
@NativeType("char *") public String signatureString(){
  return nsignatureString(address());
}
