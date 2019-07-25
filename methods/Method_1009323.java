@Override public void close() throws IOException {
  try {
    socket.shutdownInput();
  }
 catch (  Exception e) {
  }
  try {
    socket.shutdownOutput();
  }
 catch (  Exception e) {
  }
  socket.close();
}
