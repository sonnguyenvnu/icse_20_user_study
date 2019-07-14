@Override public SofaResponse decodeObject(AbstractByteBuf data,Map<String,String> context) throws SofaRpcException {
  try {
    UnsafeByteArrayInputStream inputStream=new UnsafeByteArrayInputStream(data.array());
    Hessian2Input input=new Hessian2Input(inputStream);
    Object object;
    boolean genericSerialize=context != null && isGenericResponse(context.get(RemotingConstants.HEAD_GENERIC_TYPE));
    if (genericSerialize) {
      input.setSerializerFactory(genericSerializerFactory);
      GenericObject genericObject=(GenericObject)input.readObject();
      SofaResponse sofaResponse=new SofaResponse();
      sofaResponse.setErrorMsg((String)genericObject.getField("errorMsg"));
      sofaResponse.setAppResponse(genericObject.getField("appResponse"));
      sofaResponse.setResponseProps((Map<String,String>)genericObject.getField("responseProps"));
      object=sofaResponse;
    }
 else {
      input.setSerializerFactory(serializerFactory);
      object=input.readObject();
    }
    input.close();
    return (SofaResponse)object;
  }
 catch (  IOException e) {
    throw buildDeserializeError(e.getMessage(),e);
  }
}
