public void get(ResultCallback<Frame> resultCallback){
  HttpRequestProvider requestProvider=httpGetRequestProvider();
  HttpResponseHandler responseHandler=new HttpResponseHandler(requestProvider,resultCallback);
  FramedResponseStreamHandler streamHandler=new FramedResponseStreamHandler(resultCallback);
  Channel channel=getChannel();
  channel.pipeline().addLast(responseHandler);
  channel.pipeline().addLast(streamHandler);
  sendRequest(requestProvider,channel);
}
