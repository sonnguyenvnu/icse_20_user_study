@Override public <Request extends RequestCommand>boolean deserializeContent(Request request) throws DeserializationException {
  if (request instanceof RpcRequestCommand) {
    RpcRequestCommand requestCommand=(RpcRequestCommand)request;
    Object header=requestCommand.getRequestHeader();
    if (!(header instanceof Map)) {
      throw new DeserializationException("Head of request is null or is not map");
    }
    Map<String,String> headerMap=(Map<String,String>)header;
    byte[] content=requestCommand.getContent();
    if (content == null || content.length == 0) {
      throw new DeserializationException("Content of request is null");
    }
    try {
      String service=headerMap.get(RemotingConstants.HEAD_SERVICE);
      ClassLoader oldClassLoader=Thread.currentThread().getContextClassLoader();
      ClassLoader serviceClassLoader=ReflectCache.getServiceClassLoader(service);
      try {
        Thread.currentThread().setContextClassLoader(serviceClassLoader);
        Serializer rpcSerializer=com.alipay.sofa.rpc.codec.SerializerFactory.getSerializer(requestCommand.getSerializer());
        Object sofaRequest=ClassUtils.forName(requestCommand.getRequestClass()).newInstance();
        rpcSerializer.decode(new ByteArrayWrapperByteBuf(requestCommand.getContent()),sofaRequest,headerMap);
        requestCommand.setRequestObject(sofaRequest);
      }
  finally {
        Thread.currentThread().setContextClassLoader(oldClassLoader);
      }
      return true;
    }
 catch (    Exception ex) {
      throw new DeserializationException(ex.getMessage(),ex);
    }
 finally {
      recordDeserializeRequest(requestCommand);
    }
  }
  return false;
}
