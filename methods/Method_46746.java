public boolean noConnect(SocketAddress socketAddress){
  for (  Channel channel : channels) {
    if (channel.remoteAddress().toString().equals(socketAddress.toString())) {
      return false;
    }
  }
  return true;
}
