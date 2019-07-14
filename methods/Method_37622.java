/** 
 * Returns all uploaded files the given request parameter has.
 */
public FileUpload[] getFiles(final String paramName){
  if (requestFiles == null) {
    return null;
  }
  return requestFiles.get(paramName);
}
