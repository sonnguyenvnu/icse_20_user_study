@Override public boolean invoke(ClientRequestContext ctx,HttpRequest req,DecodedHttpResponse res){
  if (handleEarlyCancellation(ctx,req,res)) {
    return true;
  }
  final long writeTimeoutMillis=ctx.writeTimeoutMillis();
  final long responseTimeoutMillis=ctx.responseTimeoutMillis();
  final long maxContentLength=ctx.maxResponseLength();
  assert responseDecoder != null;
  assert requestEncoder != null;
  final int numRequestsSent=++this.numRequestsSent;
  final HttpResponseWrapper wrappedRes=responseDecoder.addResponse(numRequestsSent,req,res,ctx.logBuilder(),responseTimeoutMillis,maxContentLength);
  req.subscribe(new HttpRequestSubscriber(channel,requestEncoder,numRequestsSent,req,wrappedRes,ctx,writeTimeoutMillis),channel.eventLoop(),WITH_POOLED_OBJECTS);
  if (numRequestsSent >= MAX_NUM_REQUESTS_SENT) {
    responseDecoder.disconnectWhenFinished();
    return false;
  }
 else {
    return true;
  }
}
