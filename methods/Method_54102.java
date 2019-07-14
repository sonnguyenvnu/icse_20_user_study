/** 
 * Decodes the null-terminated string stored in the  {@code achFormatHint} field. 
 */
@NativeType("char[9]") public String achFormatHintString(){
  return nachFormatHintString(address());
}
