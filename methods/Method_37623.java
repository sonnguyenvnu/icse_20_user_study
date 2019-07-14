/** 
 * Returns parameter names of all uploaded files.
 */
public Set<String> getFileParameterNames(){
  if (requestFiles == null) {
    return Collections.emptySet();
  }
  return requestFiles.keySet();
}
