public MessageContent toContent(){
  return content().withCharset(charset).withContent(content).build();
}
