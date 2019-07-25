synchronized public void init(NetworkInterface networkInterface,Router router,NetworkAddressFactory networkAddressFactory,DatagramProcessor datagramProcessor) throws InitializationException {
  this.router=router;
  this.networkAddressFactory=networkAddressFactory;
  this.datagramProcessor=datagramProcessor;
  this.multicastInterface=networkInterface;
  try {
    log.info("Creating wildcard socket (for receiving multicast datagrams) on port: " + configuration.getPort());
    multicastAddress=new InetSocketAddress(configuration.getGroup(),configuration.getPort());
    socket=new MulticastSocket(configuration.getPort());
    socket.setReuseAddress(true);
    socket.setReceiveBufferSize(32768);
    log.info("Joining multicast group: " + multicastAddress + " on network interface: " + multicastInterface.getDisplayName());
    socket.joinGroup(multicastAddress,multicastInterface);
  }
 catch (  Exception ex) {
    throw new InitializationException("Could not initialize " + getClass().getSimpleName() + ": " + ex);
  }
}
