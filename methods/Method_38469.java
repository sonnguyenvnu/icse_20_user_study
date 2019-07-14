/** 
 * Returns full "Content-Length" header or <code>null</code> if not set. Returned value is raw and unchecked, exactly the same as it was specified or received. It may be even invalid.
 */
public String contentLength(){
  return header(HEADER_CONTENT_LENGTH);
}
