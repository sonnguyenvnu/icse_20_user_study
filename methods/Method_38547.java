/** 
 * Closes socket silently.
 */
private void closeSocket(final Socket socket){
  try {
    if (socket != null) {
      socket.close();
    }
  }
 catch (  Exception ignore) {
  }
}
