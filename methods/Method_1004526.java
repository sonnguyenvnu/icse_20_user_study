public void execute(Runnable command){
  startTask();
  try {
    command.run();
  }
  finally {
    endTask();
  }
}
