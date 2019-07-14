protected void removeIndex(int index){
synchronized (clientsLock) {
    clientCount--;
    for (int i=index; i < clientCount; i++) {
      clients[i]=clients[i + 1];
    }
    clients[clientCount]=null;
  }
}
