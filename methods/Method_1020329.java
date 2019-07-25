@Override public Message receive() throws IOException, InterruptedIOException {
  while (noMessages && !closed) {
    try {
      Thread.sleep(60000);
    }
 catch (    InterruptedException e) {
      e.printStackTrace();
    }
  }
  MessageImpl message=new MessageImpl(MessageConnection.TEXT_MESSAGE,name);
  message.setPayloadText("sms");
  noMessages=true;
  return message;
}
