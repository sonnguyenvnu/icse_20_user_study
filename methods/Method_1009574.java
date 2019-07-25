synchronized public void init(InetAddress bindAddress,Router router,DatagramProcessor datagramProcessor) throws InitializationException {
  this.router=router;
  this.datagramProcessor=datagramProcessor;
  try {
    log.info("Creating bound socket (for datagram input/output) on: " + bindAddress);
    localAddress=new InetSocketAddress(bindAddress,0);
    socket=new MulticastSocket(localAddress);
    socket.setTimeToLive(configuration.getTimeToLive());
    socket.setReceiveBufferSize(262144);
  }
 catch (  Exception ex) {
    throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + ex);
  }
}
