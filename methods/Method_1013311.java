public void stop(){
synchronized (tlcServer) {
    tlcServer.setDone();
    tlcServer.stateQueue.finishAll();
    tlcServer.notifyAll();
  }
}
