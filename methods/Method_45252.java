private MessageContent asMessageContent(final byte[] content){
  MessageContent.Builder builder=content().withContent(content);
  if (charset != null) {
    builder.withCharset(charset);
  }
  return builder.build();
}
