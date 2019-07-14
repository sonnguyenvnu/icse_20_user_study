protected void doSend(final SofaRequest request,AbstractHttpClientHandler callback,final int timeoutMills){
  AbstractByteBuf data=null;
  try {
    byte serializeType=request.getSerializeType();
    Serializer serializer=SerializerFactory.getSerializer(serializeType);
    data=serializer.encode(request,null);
    request.setData(data);
    RpcInternalContext.getContext().setAttachment(RpcConstants.INTERNAL_KEY_REQ_SIZE,data.readableBytes());
    FullHttpRequest httpRequest=convertToHttpRequest(request);
    final int requestId=sendHttpRequest(httpRequest,callback);
    if (request.isAsync()) {
      TIMEOUT_TIMER.newTimeout(new TimerTask(){
        @Override public void run(        Timeout timeout) throws Exception {
          Map.Entry<ChannelFuture,AbstractHttpClientHandler> entry=responseChannelHandler.removePromise(requestId);
          if (entry != null) {
            ClientHandler handler=entry.getValue();
            Exception e=timeoutException(request,timeoutMills,null);
            handler.onException(e);
          }
        }
      }
,timeoutMills,TimeUnit.MILLISECONDS);
    }
  }
  finally {
    if (data != null) {
      data.release();
    }
  }
}
