public void invoke(IManagerCommand command){
  if (isManagerThread()) {
    myThread.processCommand(command);
  }
 else {
    schedule(command);
  }
}
