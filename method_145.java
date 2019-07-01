private void _XXXXX_(Endpoint endpoint,DatagramChannel channel){
  try {
    byte[] data=new byte[endpoint.getMaxPacketSize()];
    ByteBuffer buffer=ByteBuffer.wrap(data);
    InetSocketAddress address=(InetSocketAddress)channel.receive(buffer);
    int length=buffer.position();
    if (log.isDebugEnabled()) {
      log.debug("Received packet from " + address + " with length "+ length);
    }
    callback.receive(endpoint,data,length,new UDPOutTransportInfo(address));
  }
 catch (  IOException ex) {
    endpoint.getMetrics().incrementFaultsReceiving();
    log.error("Error receiving UDP packet",ex);
  }
}