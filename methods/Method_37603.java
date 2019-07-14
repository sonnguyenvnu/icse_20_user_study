/** 
 * Determines if upload is allowed.
 */
protected boolean checkUpload() throws IOException {
  if (fileExtensions != null) {
    if (!matchFileExtension()) {
      return false;
    }
  }
  return true;
}
