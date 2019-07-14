public final boolean isAlive(){
  return wasStarted() && !this.myServerSocket.isClosed() && this.myThread.isAlive();
}
