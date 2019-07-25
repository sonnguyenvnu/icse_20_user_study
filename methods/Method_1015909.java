@Override public void chat(String message){
  Preconditions.checkState(server != null,"Not connected to server");
  server.getCh().write(new Chat(message));
}
