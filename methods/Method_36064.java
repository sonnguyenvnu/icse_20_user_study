/** 
 * Returns a RequestPatternBuilder matching a given Request
 */
@Override public RequestPatternBuilder apply(Request request){
  final RequestPatternBuilder builder=new RequestPatternBuilder(request.getMethod(),urlEqualTo(request.getUrl()));
  if (headers != null && !headers.isEmpty()) {
    for (    Map.Entry<String,CaptureHeadersSpec> header : headers.entrySet()) {
      String headerName=header.getKey();
      if (request.containsHeader(headerName)) {
        CaptureHeadersSpec spec=header.getValue();
        StringValuePattern headerMatcher=new EqualToPattern(request.getHeader(headerName),spec.getCaseInsensitive());
        builder.withHeader(headerName,headerMatcher);
      }
    }
  }
  byte[] body=request.getBody();
  if (bodyPatternFactory != null && body != null && body.length > 0) {
    builder.withRequestBody(bodyPatternFactory.forRequest(request));
  }
  return builder;
}
