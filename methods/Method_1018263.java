private static boolean available(int port){
  if (port < MIN_PORT_NUMBER || port > MAX_PORT_NUMBER) {
    throw new IllegalArgumentException("Invalid port: " + port);
  }
  try (ServerSocket ss=new ServerSocket(port)){
    ss.setReuseAddress(true);
    return true;
  }
 catch (  IOException e) {
  }
  return false;
}
