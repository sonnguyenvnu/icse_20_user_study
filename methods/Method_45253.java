protected final String filename(final Request request){
  MessageContent messageContent=this.filename.readFor(request);
  return messageContent.toString();
}
