/** 
 * Invoked on incoming connection. By default returns  {@link HttpTunnelConnection}to handle the connection. May be used to return custom handlers.
 */
protected Runnable onSocketConnection(final Socket socket){
  return new HttpTunnelConnection(socket);
}
