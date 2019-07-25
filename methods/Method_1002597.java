void accept(SelectionKey key){
  ServerSocketChannel ch=(ServerSocketChannel)key.channel();
  SocketChannel s;
  try {
    while ((s=ch.accept()) != null) {
      s.configureBlocking(false);
      HttpAtta atta=new HttpAtta(maxBody,maxLine,proxyProtocolOption);
      SelectionKey k=s.register(selector,OP_READ,atta);
      atta.channel=new AsyncChannel(k,this);
    }
  }
 catch (  Exception e) {
    errorLogger.log("accept incoming request",e);
    eventLogger.log(eventNames.serverAcceptError);
  }
}
