/** 
 * Since CloudStreamServer and Streamer both uses the same port, shutdown the Streamer before acquiring the port.
 * @return ServerSocket
 */
private ServerSocket tryBind(int port) throws IOException {
  ServerSocket socket;
  try {
    socket=new ServerSocket(port);
  }
 catch (  BindException ifPortIsOccupiedByStreamer) {
    Streamer.getInstance().stop();
    socket=new ServerSocket(port);
  }
  return socket;
}
