@Override public DatagramPacket encapsulate(DatagramPacket packet,SocketAddress destination) throws SocksException {
  if (destination instanceof InetSocketAddress) {
    InetSocketAddress destinationAddress=(InetSocketAddress)destination;
    final byte[] data=packet.getData();
    final InetAddress remoteServerAddress=packet.getAddress();
    final byte[] addressBytes=remoteServerAddress.getAddress();
    final int ADDRESS_LENGTH=remoteServerAddress.getAddress().length;
    final int remoteServerPort=packet.getPort();
    byte[] buffer=new byte[6 + packet.getLength() + ADDRESS_LENGTH];
    buffer[0]=buffer[1]=0;
    buffer[2]=0;
    buffer[3]=(byte)(ADDRESS_LENGTH == 4 ? AddressType.IPV4 : AddressType.IPV6);
    System.arraycopy(addressBytes,0,buffer,4,ADDRESS_LENGTH);
    buffer[4 + ADDRESS_LENGTH]=SocksUtil.getFirstByteFromInt(remoteServerPort);
    buffer[5 + ADDRESS_LENGTH]=SocksUtil.getSecondByteFromInt(remoteServerPort);
    System.arraycopy(data,0,buffer,6 + ADDRESS_LENGTH,packet.getLength());
    return new DatagramPacket(buffer,buffer.length,destinationAddress.getAddress(),destinationAddress.getPort());
  }
 else {
    throw new IllegalArgumentException("Only support java.net.InetSocketAddress");
  }
}
