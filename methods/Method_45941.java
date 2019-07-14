@Override public void decodeObjectByTemplate(AbstractByteBuf data,Map<String,String> context,SofaResponse template) throws SofaRpcException {
  try {
    UnsafeByteArrayInputStream inputStream=new UnsafeByteArrayInputStream(data.array());
    Hessian2Input input=new Hessian2Input(inputStream);
    boolean genericSerialize=context != null && isGenericResponse(context.get(RemotingConstants.HEAD_GENERIC_TYPE));
    if (genericSerialize) {
      input.setSerializerFactory(genericSerializerFactory);
      GenericObject genericObject=(GenericObject)input.readObject();
      template.setErrorMsg((String)genericObject.getField("errorMsg"));
      template.setAppResponse(genericObject.getField("appResponse"));
      template.setResponseProps((Map<String,String>)genericObject.getField("responseProps"));
    }
 else {
      input.setSerializerFactory(serializerFactory);
      SofaResponse tmp=(SofaResponse)input.readObject();
      template.setErrorMsg(tmp.getErrorMsg());
      template.setAppResponse(tmp.getAppResponse());
      template.setResponseProps(tmp.getResponseProps());
    }
    input.close();
  }
 catch (  IOException e) {
    throw buildDeserializeError(e.getMessage(),e);
  }
}
