private static boolean hasContent(final HttpMessage message){
  String lengthText=message.getHeader(HttpHeaders.CONTENT_LENGTH);
  if (lengthText != null) {
    return true;
  }
  MessageContent content=message.getContent();
  return content != null && content.hasContent();
}
