@Override public void closed(ClientHandler clientHandler){
  this.running.remove(clientHandler);
}
