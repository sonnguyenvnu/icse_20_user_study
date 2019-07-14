public void receiveHttpResponse(FullHttpResponse msg){
  HttpHeaders headers=msg.headers();
  ByteBuf content=msg.content();
  NettyByteBuffer data=new NettyByteBuffer(content);
  try {
    if (msg.status() == HttpResponseStatus.OK) {
      final SofaResponse response=new SofaResponse();
      String isError=headers.get(RemotingConstants.HEAD_RESPONSE_ERROR);
      if (CommonUtils.isTrue(isError)) {
        String errorMsg=StringSerializer.decode(data.array());
        Throwable throwable=new SofaRpcException(RpcErrorType.SERVER_BIZ,errorMsg);
        response.setAppResponse(throwable);
      }
 else {
        if (data.readableBytes() > 0) {
          byte serializeType;
          String codeName=headers.get(RemotingConstants.HEAD_SERIALIZE_TYPE);
          if (codeName != null) {
            serializeType=HttpTransportUtils.getSerializeTypeByName(codeName);
          }
 else {
            String contentType=StringUtils.toString(headers.get(HttpHeaderNames.CONTENT_TYPE));
            serializeType=HttpTransportUtils.getSerializeTypeByContentType(contentType);
          }
          response.setSerializeType(serializeType);
          content.retain();
          response.setData(data);
        }
      }
      onResponse(response);
    }
 else {
      String errorMsg=StringSerializer.decode(data.array());
      Throwable throwable=new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,errorMsg);
      onException(throwable);
    }
  }
 catch (  final Exception e) {
    onException(e);
  }
}
