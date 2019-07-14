public String getText(long writerId){
  SendBroadcastWriter writer=findWriter(writerId);
  return writer != null ? writer.getText() : null;
}
