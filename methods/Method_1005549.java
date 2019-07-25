/** 
 * A connect action was unsuccessful, notify the user and update client history
 * @param exception This argument is not used
 */
private void connect(Throwable exception){
  Connection c=Connections.getInstance(context).getConnection(clientHandle);
  c.changeConnectionStatus(Connection.ConnectionStatus.ERROR);
  c.addAction("Client failed to connect");
  System.out.println("Client failed to connect");
}
