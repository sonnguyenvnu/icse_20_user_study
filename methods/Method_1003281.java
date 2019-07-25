/** 
 * Get the base path for the given wrapped path.
 * @param path the path including the scheme prefix
 * @return the base file path
 */
protected FilePath unwrap(String path){
  return FilePath.get(path.substring(getScheme().length() + 1));
}
