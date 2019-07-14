private RequestPattern buildRequestPatternFrom(Request request){
  RequestPatternBuilder builder=newRequestPattern(request.getMethod(),urlEqualTo(request.getUrl()));
  if (!headersToMatch.isEmpty()) {
    for (    HttpHeader header : request.getHeaders().all()) {
      if (headersToMatch.contains(header.caseInsensitiveKey())) {
        builder.withHeader(header.key(),equalTo(header.firstValue()));
      }
    }
  }
  if (request.isMultipart()) {
    for (    Request.Part part : request.getParts()) {
      builder.withRequestBodyPart(valuePatternForPart(part));
    }
  }
 else {
    String body=request.getBodyAsString();
    if (!body.isEmpty()) {
      builder.withRequestBody(valuePatternForContentType(request));
    }
  }
  return builder.build();
}
