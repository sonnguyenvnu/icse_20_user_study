/** 
 * Returns an object which would be converted into response body.
 */
default Optional<T> content(){
  return Optional.empty();
}
