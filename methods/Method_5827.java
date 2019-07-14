@Override public long open(DataSpec dataSpec) throws UdpDataSourceException {
  uri=dataSpec.uri;
  String host=uri.getHost();
  int port=uri.getPort();
  transferInitializing(dataSpec);
  try {
    address=InetAddress.getByName(host);
    socketAddress=new InetSocketAddress(address,port);
    if (address.isMulticastAddress()) {
      multicastSocket=new MulticastSocket(socketAddress);
      multicastSocket.joinGroup(address);
      socket=multicastSocket;
    }
 else {
      socket=new DatagramSocket(socketAddress);
    }
  }
 catch (  IOException e) {
    throw new UdpDataSourceException(e);
  }
  try {
    socket.setSoTimeout(socketTimeoutMillis);
  }
 catch (  SocketException e) {
    throw new UdpDataSourceException(e);
  }
  opened=true;
  transferStarted(dataSpec);
  return C.LENGTH_UNSET;
}
