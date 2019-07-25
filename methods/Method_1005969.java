public void delete(){
  HttpRequestProvider requestProvider=httpDeleteRequestProvider();
  ResponseCallback<Void> callback=new ResponseCallback<Void>();
  HttpResponseHandler responseHandler=new HttpResponseHandler(requestProvider,callback);
  Channel channel=getChannel();
  channel.pipeline().addLast(responseHandler);
  sendRequest(requestProvider,channel);
  callback.awaitResult();
}
