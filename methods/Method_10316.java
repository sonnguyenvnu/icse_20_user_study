/** 
 * Activate supported protocols on the socket.
 * @param socket    The socket on which to activate secure protocols.
 */
private void enableSecureProtocols(Socket socket){
  SSLParameters params=sslContext.getSupportedSSLParameters();
  ((SSLSocket)socket).setEnabledProtocols(params.getProtocols());
}
