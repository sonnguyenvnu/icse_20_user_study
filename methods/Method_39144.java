/** 
 * Applies content type to the response. Called before output stream or a writer are used.
 */
private void applyContentType(){
  if (mimeType != null) {
    super.setContentType(mimeType);
    if (characterEncoding != null) {
      super.setCharacterEncoding(characterEncoding);
    }
  }
}
