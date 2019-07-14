private MultipartValuePattern valuePatternForPart(Request.Part part){
  MultipartValuePatternBuilder builder=new MultipartValuePatternBuilder().withName(part.getName()).matchingType(MultipartValuePattern.MatchingType.ALL);
  if (!headersToMatch.isEmpty()) {
    Collection<HttpHeader> all=part.getHeaders().all();
    for (    HttpHeader httpHeader : all) {
      if (headersToMatch.contains(httpHeader.caseInsensitiveKey())) {
        builder.withHeader(httpHeader.key(),equalTo(httpHeader.firstValue()));
      }
    }
  }
  HttpHeader contentType=part.getHeader("Content-Type");
  if (!contentType.isPresent() || contentType.firstValue().contains("text")) {
    builder.withBody(equalTo(part.getBody().asString()));
  }
 else   if (contentType.firstValue().contains("json")) {
    builder.withBody(equalToJson(part.getBody().asString(),true,true));
  }
 else   if (contentType.firstValue().contains("xml")) {
    builder.withBody(equalToXml(part.getBody().asString()));
  }
 else {
    builder.withBody(binaryEqualTo(part.getBody().asBytes()));
  }
  return builder.build();
}
