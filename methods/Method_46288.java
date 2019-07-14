protected void decode(SofaResponse response){
  AbstractByteBuf byteBuffer=response.getData();
  if (byteBuffer != null) {
    try {
      Map<String,String> context=new HashMap<String,String>(4);
      if (response.isError()) {
        context.put(RemotingConstants.HEAD_RESPONSE_ERROR,response.isError() + "");
        String errorMsg=StringSerializer.decode(byteBuffer.array());
        response.setAppResponse(new SofaRpcException(RpcErrorType.SERVER_UNDECLARED_ERROR,errorMsg));
      }
 else {
        context.put(RemotingConstants.HEAD_TARGET_SERVICE,request.getTargetServiceUniqueName());
        context.put(RemotingConstants.HEAD_METHOD_NAME,request.getMethodName());
        Serializer serializer=SerializerFactory.getSerializer(response.getSerializeType());
        serializer.decode(byteBuffer,response,context);
      }
    }
  finally {
      byteBuffer.release();
      response.setData(null);
    }
  }
}
