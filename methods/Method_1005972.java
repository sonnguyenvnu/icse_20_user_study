public <T>void post(final Object entity,TypeReference<T> typeReference,final ResultCallback<T> resultCallback){
  HttpRequestProvider requestProvider=httpPostRequestProvider(entity);
  Channel channel=getChannel();
  JsonResponseCallbackHandler<T> jsonResponseHandler=new JsonResponseCallbackHandler<T>(typeReference,resultCallback);
  HttpResponseHandler responseHandler=new HttpResponseHandler(requestProvider,resultCallback);
  channel.pipeline().addLast(responseHandler);
  channel.pipeline().addLast(new JsonObjectDecoder(3 * 1024 * 1024));
  channel.pipeline().addLast(jsonResponseHandler);
  sendRequest(requestProvider,channel);
  return;
}
