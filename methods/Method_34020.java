/** 
 * Whether to allow the specified HTTP method.
 * @param method The HTTP method to check for allowing.
 * @return Whether to allow the specified method.
 */
protected boolean allowMethod(String method){
  return allowedMethods.contains(method);
}
