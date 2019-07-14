/** 
 * Check if the host directives explicitly disallow visiting path.
 * @param path The path to check
 * @return True if the path is explicity disallowed, false otherwise
 */
public boolean disallows(String path){
  return checkAccess(path) == DISALLOWED;
}
