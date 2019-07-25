/** 
 * Starts a UDP relay server.
 * @return Server bind socket address.
 * @throws SocketException If a SOCKS protocol error occurred.
 */
public SocketAddress start() throws SocketException {
  running=true;
  server=new DatagramSocket();
  if (networkMonitor != null) {
    server=new MonitorDatagramSocketWrapper(server,networkMonitor);
  }
  SocketAddress socketAddress=server.getLocalSocketAddress();
  thread=new Thread(this);
  thread.start();
  return socketAddress;
}
