@Override public boolean process(@NotNull QueryStringDecoder decoder,@NotNull FullHttpRequest request,@NotNull ChannelHandlerContext context) throws IOException {
  jetbrains.mps.ide.httpsupport.manager.plugin.HttpRequest boxedRequest;
  try {
    boxedRequest=new jetbrains.mps.ide.httpsupport.manager.plugin.HttpRequest(request,decoder,context.channel());
  }
 catch (  URISyntaxException exception) {
    return false;
  }
  for (  IHttpRequestHandlerFactory handlerFactory : Sequence.fromIterable(new ExtensionPoint<IHttpRequestHandlerFactory>("jetbrains.mps.ide.httpsupport.HttpRequestHandlerEP").getObjects())) {
    IHttpRequestHandler handler=handlerFactory.create(boxedRequest);
    if (handler.canHandle()) {
      try {
        handler.handle();
      }
 catch (      Throwable e) {
        handleException(e,handlerFactory,boxedRequest);
      }
 finally {
        return true;
      }
    }
  }
  return false;
}
