/** 
 * Any modes that extend JavaMode can override this method to add additional JARs to be included in the classpath for code completion and error checking
 * @return searchPath: file-paths separated by File.pathSeparatorChar
 */
public String getSearchPath(){
  return getCoreLibrary().getJarPath();
}
