private InputStream getKeyStore(){
  MessageContent messageContent=resource.readFor(null);
  return messageContent.toInputStream();
}
