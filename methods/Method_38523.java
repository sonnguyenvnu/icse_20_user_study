/** 
 * Creates response  {@link jodd.http.Buffer buffer}.
 */
@Override protected Buffer buffer(final boolean fullResponse){
  Buffer formBuffer=formBuffer();
  Buffer response=new Buffer();
  response.append(httpVersion).append(SPACE).append(statusCode).append(SPACE).append(statusPhrase).append(CRLF);
  populateHeaderAndBody(response,formBuffer,fullResponse);
  return response;
}
