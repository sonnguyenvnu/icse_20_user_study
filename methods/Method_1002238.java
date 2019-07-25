/** 
 * Removes the named query parameter. <p> If the parameter has multiple values, all its values are removed.
 * @param name name of the parameter to remove
 * @return a reference to this object
 */
public UrlEncodedQueryStringBase remove(final String name){
  appendOrSet(name,null,false);
  return this;
}
