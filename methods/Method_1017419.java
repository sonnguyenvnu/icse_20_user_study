@Override public HttpResponse execute(final HttpRequest request) throws HttpClientException {
  HttpResponse response=null;
  try {
    final URI uri=request.getURI();
    final SettableFuture<HttpResponse> future=new SettableFuture<HttpResponse>();
    ChannelFutureListener listener=new ChannelFutureListener(){
      @Override public void operationComplete(      ChannelFuture channelFuture) throws Exception {
        if (channelFuture.isSuccess()) {
          Channel channel=channelFuture.channel();
          if ("https".equals(uri.getScheme())) {
            SSLContext sslContext;
            if (params != null && params.getSSLContext() != null) {
              sslContext=params.getSSLContext();
            }
 else {
              sslContext=HttpClientFactory.allowSSLContext();
            }
            SSLEngine sslEngine=sslContext.createSSLEngine();
            sslEngine.setUseClientMode(true);
            channel.pipeline().addFirst(new SslHandler(sslEngine));
          }
          channel.pipeline().addLast(new RequestHandler(future));
          DefaultHttpRequest uriRequest=createRequest(request);
          channel.writeAndFlush(uriRequest);
        }
 else {
          future.setException(channelFuture.cause());
        }
      }
    }
;
    InetSocketAddress address=params != null && params.getProxy() != null ? (InetSocketAddress)params.getProxy().address() : new InetSocketAddress(InetAddress.getByName(uri.getHost()),getPort(uri));
    bootstrap.connect(address).addListener(listener);
    response=future.get();
    handleResponse(response);
  }
 catch (  IOException e) {
    throw new HttpClientException("I/O error on " + request.getMethod().name() + " request for \"" + request.getURI().toString(),e);
  }
catch (  InterruptedException e) {
    throw new HttpClientException("Execute error on " + request.getMethod().name() + " request for \"" + request.getURI().toString(),e);
  }
catch (  ExecutionException e) {
    throw new HttpClientException("Execute error on " + request.getMethod().name() + " request for \"" + request.getURI().toString(),e);
  }
 finally {
    if (response != null) {
      response.close();
    }
  }
  return response;
}
