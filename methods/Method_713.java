/** 
 * Method that JAX-RS container calls to try to check whether values of given type (and media type) can be deserialized by this provider.
 */
public boolean isReadable(Class<?> type,Type genericType,Annotation[] annotations,MediaType mediaType){
  if (!hasMatchingMediaType(mediaType)) {
    return false;
  }
  if (!isAssignableFrom(type,DEFAULT_UNREADABLES))   return false;
  return isValidType(type,annotations);
}
