private void report(String message,Exception cause){
  Message m=new Message(MessageKind.ERROR,getClass(),message);
  m.setHintObject(SNodeOperations.getPointer(myModule));
  m.setException(cause);
  myReporter.handle(m);
}
