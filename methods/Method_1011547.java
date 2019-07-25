public void schedule(IManagerCommand command){
  if (myClosed) {
    command.cancel();
  }
 else {
    myCommandQueue.offer(command);
  }
}
