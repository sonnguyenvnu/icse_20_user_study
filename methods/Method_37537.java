/** 
 * Specifies the search path. Throws an exception if URI is invalid.
 */
public FindFile searchPath(final URI searchPath){
  File file;
  try {
    file=new File(searchPath);
  }
 catch (  Exception ex) {
    throw new FindFileException("URI error: " + searchPath,ex);
  }
  addPath(file);
  return this;
}
