/** 
 * Gets an attribute specified by the given name from request.
 * @param name the given name
 * @return attribute, returns {@code null} if not found
 */
public Object attr(final String name){
  return request.getAttribute(name);
}
