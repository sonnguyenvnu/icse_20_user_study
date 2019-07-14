@Override public String getRemoteKey() throws RpcException {
  int size=SocketManager.getInstance().currentSize();
  if (size == 0) {
    throw new RpcException(RpcException.NON_TX_MANAGER,"not can used connection");
  }
  int randomIndex=random.nextInt(size);
  int index=0;
  for (  Channel channel : SocketManager.getInstance().getChannels()) {
    if (index == randomIndex) {
      return channel.remoteAddress().toString();
    }
    index++;
  }
  throw new RpcException("channels was empty.");
}
