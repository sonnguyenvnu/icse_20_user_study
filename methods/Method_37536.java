/** 
 * Specifies the search path. If provided path contains {@link File#pathSeparator} than string will be tokenizedand each part will be added separately as a search path. 
 */
public FindFile searchPath(final String searchPath){
  if (searchPath.indexOf(File.pathSeparatorChar) != -1) {
    String[] paths=StringUtil.split(searchPath,File.pathSeparator);
    for (    String path : paths) {
      addPath(new File(path));
    }
  }
 else {
    addPath(new File(searchPath));
  }
  return this;
}
