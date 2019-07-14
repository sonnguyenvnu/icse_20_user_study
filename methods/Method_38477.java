/** 
 * Resolves form encodings.
 */
protected String resolveFormEncoding(){
  String formEncoding=charset;
  if (formEncoding == null) {
    formEncoding=this.formEncoding;
  }
  return formEncoding;
}
