/** 
 * Resolve a relative path (as returned by the java watcher) to an absolute path
 * @param other the other
 * @return the path
 */
public Path resolve(Path other){
  return watched.resolve(other);
}
