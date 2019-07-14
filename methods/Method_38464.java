/** 
 * Returns collection of all header names. Depends on {@link #capitalizeHeaderKeys()} flag.
 */
public Collection<String> headerNames(){
  return headers.names();
}
