/** 
 * Get the Documents and Settings\name\My Documents\Processing folder. 
 */
public File getDefaultSketchbookFolder() throws Exception {
  String documentsPath=getDocumentsPath();
  if (documentsPath != null) {
    return new File(documentsPath,APP_NAME);
  }
  return null;
}
