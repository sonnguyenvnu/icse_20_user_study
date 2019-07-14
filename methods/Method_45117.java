private MessageContent requireResponseContent(final Request request){
  MessageContent content=responseContent(request);
  if (content == null) {
    throw new IllegalStateException("Message content is expected. Please make sure responseContent method has been implemented correctly");
  }
  return content;
}
