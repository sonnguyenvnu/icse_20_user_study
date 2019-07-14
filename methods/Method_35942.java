private Socket socket(){
  HttpChannel httpChannel=response.getHttpOutput().getHttpChannel();
  ChannelEndPoint ep=(ChannelEndPoint)httpChannel.getEndPoint();
  return ((SocketChannel)ep.getChannel()).socket();
}
