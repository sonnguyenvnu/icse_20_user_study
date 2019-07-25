public void post(final String msg){
  SmartQQClient client=getClient();
  if (this.contact != null) {
    QQMessage m=client.createMessage(msg,contact);
    client.sendMessage(m,this.contact);
  }
}
