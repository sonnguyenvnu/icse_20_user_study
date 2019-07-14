public void rollbackTimeout(){
  try {
    socket.setSoTimeout(soTimeout);
  }
 catch (  SocketException ex) {
    broken=true;
    throw new JedisConnectionException(ex);
  }
}
