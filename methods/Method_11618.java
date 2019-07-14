public static boolean validateProxy(Proxy p){
  Socket socket=null;
  try {
    socket=new Socket();
    InetSocketAddress endpointSocketAddr=new InetSocketAddress(p.getHost(),p.getPort());
    socket.connect(endpointSocketAddr,3000);
    return true;
  }
 catch (  IOException e) {
    logger.warn("FAILRE - CAN not connect!  remote: " + p);
    return false;
  }
 finally {
    if (socket != null) {
      try {
        socket.close();
      }
 catch (      IOException e) {
        logger.warn("Error occurred while closing socket of validating proxy",e);
      }
    }
  }
}
