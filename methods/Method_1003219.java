/** 
 * Check if this socket may connect to this server. Remote connections are not allowed if the flag allowOthers is set.
 * @param socket the socket
 * @return true if this client may connect
 */
boolean allow(Socket socket){
  if (allowOthers) {
    return true;
  }
  try {
    return NetUtils.isLocalAddress(socket);
  }
 catch (  UnknownHostException e) {
    traceError(e);
    return false;
  }
}
