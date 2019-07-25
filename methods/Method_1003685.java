private ChannelFuture pre(HttpResponseStatus responseStatus,boolean flushHeaders){
  if (transmitted.compareAndSet(false,true)) {
    stopTime=clock.instant();
    try {
      if (responseHeaders.contains(HttpHeaderNames.CONNECTION,HttpHeaderValues.CLOSE,true)) {
        isKeepAlive=false;
      }
 else       if (!isKeepAlive) {
        forceCloseConnection();
      }
      HttpResponse headersResponse=new CustomHttpResponse(responseStatus,responseHeaders);
      if (mustHaveBody(responseStatus) && isKeepAlive && HttpUtil.getContentLength(headersResponse,-1) == -1 && !HttpUtil.isTransferEncodingChunked(headersResponse)) {
        HttpUtil.setTransferEncodingChunked(headersResponse,true);
      }
      if (channel.isOpen()) {
        if (flushHeaders) {
          return channel.writeAndFlush(headersResponse);
        }
 else {
          return channel.write(headersResponse);
        }
      }
 else {
        return null;
      }
    }
 catch (    Exception e) {
      LOGGER.warn("Error finalizing response",e);
      return null;
    }
  }
 else {
    String msg="attempt at double transmission for: " + ratpackRequest.getRawUri();
    LOGGER.warn(msg,new DoubleTransmissionException(msg));
    return null;
  }
}
