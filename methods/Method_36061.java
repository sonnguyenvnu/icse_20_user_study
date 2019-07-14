/** 
 * If request body was JSON or XML, use "equalToJson" or "equalToXml" (respectively) in the RequestPattern so it's easier to read. Otherwise, just use "equalTo"
 */
@Override public ContentPattern<?> forRequest(Request request){
  final String mimeType=request.getHeaders().getContentTypeHeader().mimeTypePart();
  if (mimeType != null) {
    if (mimeType.contains("json")) {
      return new EqualToJsonPattern(request.getBodyAsString(),ignoreArrayOrder,ignoreExtraElements);
    }
 else     if (mimeType.contains("xml")) {
      return new EqualToXmlPattern(request.getBodyAsString());
    }
 else     if (mimeType.equals("multipart/form-data")) {
      return new AnythingPattern();
    }
 else     if (!determineIsTextFromMimeType(mimeType)) {
      return new BinaryEqualToPattern(request.getBody());
    }
  }
  return new EqualToPattern(request.getBodyAsString(),caseInsensitive);
}
