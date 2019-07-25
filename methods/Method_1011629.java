private void report(String message){
  myReporter.handle(Message.createMessage(MessageKind.ERROR,getClass().getName(),message,SNodeOperations.getPointer(myModule)));
}
