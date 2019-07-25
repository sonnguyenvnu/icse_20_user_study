@Override public void send(Message message) throws IOException, InterruptedIOException {
  if (listener != null) {
    Connection connection=(Connection)openConnection(name,0,false);
    listener.notifyIncomingMessage(connection);
  }
}
