/** 
 * Specifies the search path. Throws an exception if URL is invalid.
 */
public FindFile searchPath(final URL searchPath){
  File file=FileUtil.toContainerFile(searchPath);
  if (file == null) {
    throw new FindFileException("URL error: " + searchPath);
  }
  addPath(file);
  return this;
}
