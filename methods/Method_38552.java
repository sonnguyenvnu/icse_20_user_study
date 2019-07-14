/** 
 * Creates a socket.
 */
public static Socket connect(final String hostname,final int port) throws IOException {
  final Socket socket=new Socket();
  socket.connect(new InetSocketAddress(hostname,port));
  return socket;
}
