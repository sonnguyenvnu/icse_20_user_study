public void push(){
  byte[] message=build();
  for (  ClientToken client : myClients) {
    client.sendToClient(message);
  }
}
