/** 
 * Returns the file if this node is from a source file, else  {@code null}.
 */
public String getFile(){
  return isURL() ? null : fileOrUrl;
}
