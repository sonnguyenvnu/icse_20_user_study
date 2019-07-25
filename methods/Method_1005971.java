public InputStream post(final Object entity){
  HttpRequestProvider requestProvider=httpPostRequestProvider(entity);
  Channel channel=getChannel();
  AsyncResultCallback<InputStream> callback=new AsyncResultCallback<>();
  HttpResponseHandler responseHandler=new HttpResponseHandler(requestProvider,callback);
  HttpResponseStreamHandler streamHandler=new HttpResponseStreamHandler(callback);
  channel.pipeline().addLast(responseHandler);
  channel.pipeline().addLast(streamHandler);
  sendRequest(requestProvider,channel);
  return callback.awaitResult();
}
