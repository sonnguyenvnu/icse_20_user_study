public void unregister(Broker host,int partition){
  ConnectionInfo info=_connections.get(host);
  info.consumer.close();
  _connections.remove(host);
}
