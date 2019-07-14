/** 
 * Since CloudStreamServer and Streamer both uses the same port, shutdown the CloudStreamer before acquiring the port.
 * @return ServerSocket
 */
private ServerSocket tryBind(int port) throws IOException {
  ServerSocket socket;
  try {
    socket=new ServerSocket(port);
  }
 catch (  BindException ifPortIsOccupiedByCloudStreamer) {
    CloudStreamer.getInstance().stop();
    socket=new ServerSocket(port);
  }
  return socket;
}
