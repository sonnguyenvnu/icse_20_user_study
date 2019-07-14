private void closeConnections(final ArrayList<ConnectionData> connections){
  if (connections == null) {
    return;
  }
  try {
    for (    ConnectionData connectionData : connections) {
      Connection connection=connectionData.connection;
      if (!connection.isClosed()) {
        connection.close();
      }
    }
  }
 catch (  SQLException ignore) {
  }
}
