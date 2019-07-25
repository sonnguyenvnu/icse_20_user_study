/** 
 * <p> Shuts down the connection by closing the socket, the writer, and the reader. </p> <p> Internal: at this point, the connection has not been given back to the connections deque, or the deque is about to be cleared. </p>
 */
@Override public void shutdown(boolean reset){
  if (reset) {
    NetworkUtil.quietlySetLinger(socket);
  }
  NetworkUtil.quietlyClose(socket);
  NetworkUtil.quietlyClose(reader);
  NetworkUtil.quietlyClose(writer);
}
