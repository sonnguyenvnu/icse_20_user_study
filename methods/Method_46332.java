private void setupHandlers(SocketChannel ch,RequestDispatcher dispatcher,RestEasyHttpRequestDecoder.Protocol protocol){
  ChannelPipeline channelPipeline=ch.pipeline();
  channelPipeline.addLast(channelHandlers.toArray(new ChannelHandler[channelHandlers.size()]));
  channelPipeline.addLast(new HttpRequestDecoder());
  channelPipeline.addLast(new HttpObjectAggregator(maxRequestSize));
  channelPipeline.addLast(new HttpResponseEncoder());
  channelPipeline.addLast(httpChannelHandlers.toArray(new ChannelHandler[httpChannelHandlers.size()]));
  channelPipeline.addLast(new RestEasyHttpRequestDecoder(dispatcher.getDispatcher(),root,protocol));
  channelPipeline.addLast(new RestEasyHttpResponseEncoder());
  channelPipeline.addLast(eventExecutor,new SofaRestRequestHandler(dispatcher));
}
