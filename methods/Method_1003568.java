/** 
 * Process  {@code Modified} according to given the supplied {@code Last-Modified}.
 * @param lastModified the last-modified timestamp in milliseconds of the resource.
 * @return true if the request does not require further processing.
 */
public boolean process(long lastModified){
  return process(null,lastModified);
}
