private void connect() throws IOException {
  if (active) {
    socket=new Socket(address,port);
  }
 else {
    waitUntilConnected();
  }
}
