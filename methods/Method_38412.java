/** 
 * Checks if header name is excluded.
 */
protected boolean isExcluded(final String header){
  return "If-Modified-Since".equalsIgnoreCase(header);
}
