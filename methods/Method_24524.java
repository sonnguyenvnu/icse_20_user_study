protected int clientIndex(Client client){
synchronized (clientsLock) {
    for (int i=0; i < clientCount; i++) {
      if (clients[i] == client) {
        return i;
      }
    }
    return -1;
  }
}
