/** 
 * Specifies the search path.
 */
public FindFile searchPaths(final URL... searchPath){
  for (  URL url : searchPath) {
    searchPath(url);
  }
  return this;
}
