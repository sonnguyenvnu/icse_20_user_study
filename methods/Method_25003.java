public final int getListeningPort(){
  return this.myServerSocket == null ? -1 : this.myServerSocket.getLocalPort();
}
