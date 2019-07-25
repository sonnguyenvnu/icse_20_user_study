@Override protected void action(AppMessage message){
  Log.d(TAG,"???????message=" + message);
  SingleMessage msg=new SingleMessage();
  msg.setMsgId(message.getHead().getMsgId());
  msg.setMsgType(message.getHead().getMsgType());
  msg.setMsgContentType(message.getHead().getMsgContentType());
  msg.setFromId(message.getHead().getFromId());
  msg.setToId(message.getHead().getToId());
  msg.setTimestamp(message.getHead().getTimestamp());
  msg.setExtend(message.getHead().getExtend());
  msg.setContent(message.getBody());
  CEventCenter.dispatchEvent(Events.CHAT_SINGLE_MESSAGE,0,0,msg);
}
