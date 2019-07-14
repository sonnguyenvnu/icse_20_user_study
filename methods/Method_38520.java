/** 
 * Prepares the request buffer.
 */
@Override protected Buffer buffer(final boolean fullRequest){
  if (header(HEADER_HOST) == null) {
    setHostHeader();
  }
  Buffer formBuffer=formBuffer();
  String queryString=queryString();
  if (header("User-Agent") == null) {
    header("User-Agent",Defaults.userAgent);
  }
  if (method.equals("POST") && (contentLength() == null)) {
    contentLength(0);
  }
  Buffer request=new Buffer();
  request.append(method).append(SPACE).append(path);
  if (query != null && !query.isEmpty()) {
    request.append('?');
    request.append(queryString);
  }
  request.append(SPACE).append(httpVersion).append(CRLF);
  populateHeaderAndBody(request,formBuffer,fullRequest);
  return request;
}
