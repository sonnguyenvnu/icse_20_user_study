private StringValuePattern valuePatternForContentType(Request request){
  String contentType=request.getHeader("Content-Type");
  if (contentType != null) {
    if (contentType.contains("json")) {
      return equalToJson(request.getBodyAsString(),true,true);
    }
 else     if (contentType.contains("xml")) {
      return equalToXml(request.getBodyAsString());
    }
  }
  return equalTo(request.getBodyAsString());
}
