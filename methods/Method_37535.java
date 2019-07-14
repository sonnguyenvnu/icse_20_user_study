/** 
 * Specifies a set of search paths.
 */
public FindFile searchPath(final File... searchPath){
  for (  File file : searchPath) {
    addPath(file);
  }
  return this;
}
