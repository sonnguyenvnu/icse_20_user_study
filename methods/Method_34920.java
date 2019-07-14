/** 
 * Check if the host directives allows visiting path.
 * @param path The path to check
 * @return True if the path is not disallowed, false if it is
 */
public boolean allows(String path){
  return checkAccess(path) != DISALLOWED;
}
