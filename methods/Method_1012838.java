public static boolean telnet(String hostname,int port){
  Socket server=null;
  try {
    server=new Socket();
    InetSocketAddress address=new InetSocketAddress(hostname,port);
    server.connect(address,TIMEOUT);
    return true;
  }
 catch (  Exception e) {
  }
 finally {
    if (server != null) {
      try {
        server.close();
      }
 catch (      Exception e) {
      }
    }
  }
  return false;
}
