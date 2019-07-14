@Override public synchronized void closeConnection(final Connection connection){
  ConnectionData connectionData=new ConnectionData(connection);
  busyConnections.remove(connectionData);
  availableConnections.add(connectionData);
  notifyAll();
}
