/** 
 * @param pathQuery A relative URI.
 * @return <code>true</code> if the given URI path and query matches the resource's path and query.
 */
public boolean matches(URI pathQuery){
  return pathQuery.equals(getPathQuery());
}
