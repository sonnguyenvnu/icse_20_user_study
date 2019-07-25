@Override public void run(){
  try {
    gateway.startup();
    fireServerStarted();
    addListener(this);
    while (!isShutdown) {
      Socket socket=sSocket.accept();
      processSocket(socket);
    }
  }
 catch (  Exception e) {
    fireServerError(e);
  }
  fireServerStopped();
  removeListener(this);
}
