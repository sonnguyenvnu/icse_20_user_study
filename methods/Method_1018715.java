public void run(){
  pythonClient.setPerThreadConnection(this);
  waitForCommands();
}
