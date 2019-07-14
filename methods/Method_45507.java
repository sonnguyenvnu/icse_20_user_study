/** 
 * ????????????????
 * @param remoteAddress ????
 * @return ??????
 */
private static InetAddress getLocalHostBySocket(InetSocketAddress remoteAddress){
  InetAddress host=null;
  try {
    Socket socket=new Socket();
    try {
      socket.connect(remoteAddress,1000);
      host=socket.getLocalAddress();
    }
  finally {
      IOUtils.closeQuietly(socket);
    }
  }
 catch (  Exception e) {
    LOGGER.warn("Can not connect to host {}, cause by :{}",remoteAddress.toString(),e.getMessage());
  }
  return host;
}
