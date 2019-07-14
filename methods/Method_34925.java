/** 
 * Check if the specified path matches this rule
 * @param path The path to match with this pattern
 * @return True when the path matches, false when it does not
 */
public boolean matches(String path){
  return this.pattern.matcher(path).matches();
}
