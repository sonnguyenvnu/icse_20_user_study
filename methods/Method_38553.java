/** 
 * Creates a socket with a timeout.
 */
public static Socket connect(final String hostname,final int port,final int connectionTimeout) throws IOException {
  final Socket socket=new Socket();
  if (connectionTimeout <= 0) {
    socket.connect(new InetSocketAddress(hostname,port));
  }
 else {
    socket.connect(new InetSocketAddress(hostname,port),connectionTimeout);
  }
  return socket;
}
