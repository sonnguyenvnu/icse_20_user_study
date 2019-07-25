/** 
 * Set the given, single header value under the given name.
 * @param headerName the header name
 * @param headerValue the header value
 * @throws UnsupportedOperationException if adding headers is not supported
 * @see #put(String,List)
 * @see #add(String,String)
 */
@Override public void set(String headerName,String headerValue){
  List<String> headerValues=new LinkedList<String>();
  headerValues.add(headerValue);
  this.headers.put(headerName,headerValues);
}
