private void report(String message,SNode anchor){
  myMsgHandler.handle(Message.createMessage(MessageKind.ERROR,this.getClass().getName(),message,anchor.getReference()));
}
