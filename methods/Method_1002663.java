static HttpSession get(Channel ch){
  final ChannelHandler lastHandler=ch.pipeline().last();
  if (lastHandler instanceof HttpSession) {
    return (HttpSession)lastHandler;
  }
  return INACTIVE;
}
