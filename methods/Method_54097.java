/** 
 * Decodes the null-terminated string stored in the  {@code data} field. 
 */
@NativeType("char[Assimp.MAXLEN]") public String dataString(){
  return ndataString(address());
}
