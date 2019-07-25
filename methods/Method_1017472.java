/** 
 * Returns a set of entries with the contained service tags and services.
 * @return the entry set
 */
@NonNull public Set<Map.Entry<String,Object>> services(){
  return scope.services();
}
