/** 
 * Creates a new HTTP response that can stream an arbitrary number of  {@link HttpObject} to the client.The first object written must be of type  {@link ResponseHeaders}.
 */
static HttpResponseWriter streaming(){
  return new DefaultHttpResponse();
}
