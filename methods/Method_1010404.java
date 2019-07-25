protected void reconcile(){
  if (this.myResult == null) {
    String msg=this.myScrName + " aborted";
    displayInfo(msg);
  }
 else   if (!(this.myResult.isSucessful())) {
    String msg=this.myScrName + " failed";
    myMessageHandler.handle(new Message(MessageKind.ERROR,msg + ". See previous messages for details."));
    displayInfo(msg);
  }
 else {
    String msg=this.myScrName + " successful";
    displayInfo(msg);
  }
}
