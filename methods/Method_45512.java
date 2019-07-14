/** 
 * ????telnet
 * @param ip      ????
 * @param port    ????
 * @param timeout ????
 * @return ?????
 */
public static boolean canTelnet(String ip,int port,int timeout){
  Socket socket=null;
  try {
    socket=new Socket();
    socket.connect(new InetSocketAddress(ip,port),timeout);
    return socket.isConnected() && !socket.isClosed();
  }
 catch (  Exception e) {
    return false;
  }
 finally {
    IOUtils.closeQuietly(socket);
  }
}
