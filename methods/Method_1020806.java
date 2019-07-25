@Override public int next(){
  try {
    final ServerSocket socket=new ServerSocket(0);
    socket.setReuseAddress(false);
    int port=socket.getLocalPort();
    socket.close();
    return port;
  }
 catch (  IOException e) {
    throw new RedisBuildingException("Could not provide ephemeral port",e);
  }
}
