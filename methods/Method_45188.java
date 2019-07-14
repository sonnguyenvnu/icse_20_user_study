public int start(final int port,final ChannelInitializer<? extends Channel> pipelineFactory){
  ServerBootstrap bootstrap=new ServerBootstrap();
  bootstrap.group(group).channel(NioServerSocketChannel.class).childHandler(pipelineFactory);
  try {
    future=bootstrap.bind(port).sync();
    SocketAddress socketAddress=future.channel().localAddress();
    return ((InetSocketAddress)socketAddress).getPort();
  }
 catch (  InterruptedException e) {
    throw new MocoException(e);
  }
}
