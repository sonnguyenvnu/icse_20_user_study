public void removeServer(int i){
synchronized (servers) {
    if (servers.size() > i)     servers.remove(i);
  }
}
