/** 
 * Returns a  {@link URI} instance for the specified, absolute URL string.
 * @param url absolute URL string, containing scheme, host and path
 * @return URI instance for the URL string
 */
protected URI getURI(String url){
  return URI.create(url).normalize();
}
