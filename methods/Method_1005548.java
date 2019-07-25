/** 
 * A disconnect action was unsuccessful, notify user and update client history
 * @param exception This argument is not used
 */
private void disconnect(Throwable exception){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  c.changeConnectionStatus(Connection.ConnectionStatus.DISCONNECTED);
  c.addAction("Disconnect Failed - an error occured");
}
