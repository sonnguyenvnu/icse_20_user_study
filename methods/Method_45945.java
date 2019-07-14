@Override public void decode(AbstractByteBuf data,Object template,Map<String,String> context) throws SofaRpcException {
  if (template == null) {
    throw buildDeserializeError("template is null!");
  }
 else {
    CustomHessianSerializer serializer=CustomHessianSerializerManager.getSerializer(template.getClass());
    if (serializer != null) {
      serializer.decodeObjectByTemplate(data,context,template);
    }
 else {
      throw buildDeserializeError(template.getClass() + " template is not supported");
    }
  }
}
