private static String contentForDump(final HttpMessage message){
  String type=message.getHeader(HttpHeaders.CONTENT_TYPE);
  if (isText(type)) {
    return message.getContent().toString();
  }
  return "<content is binary>";
}
