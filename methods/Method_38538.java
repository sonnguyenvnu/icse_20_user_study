@Override public void init() throws IOException {
  if (timeout >= 0) {
    socket.setSoTimeout(timeout);
  }
}
