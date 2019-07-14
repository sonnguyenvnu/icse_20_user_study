protected void addClient(Client client){
synchronized (clientsLock) {
    if (clientCount == clients.length) {
      clients=(Client[])PApplet.expand(clients);
    }
    clients[clientCount++]=client;
  }
}
