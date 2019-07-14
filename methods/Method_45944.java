@Override public Object decode(AbstractByteBuf data,Class clazz,Map<String,String> context) throws SofaRpcException {
  if (clazz == null) {
    throw buildDeserializeError("class is null!");
  }
 else {
    CustomHessianSerializer serializer=CustomHessianSerializerManager.getSerializer(clazz);
    if (serializer != null) {
      return serializer.decodeObject(data,context);
    }
 else {
      try {
        UnsafeByteArrayInputStream inputStream=new UnsafeByteArrayInputStream(data.array());
        Hessian2Input input=new Hessian2Input(inputStream);
        input.setSerializerFactory(serializerFactory);
        Object object=input.readObject();
        input.close();
        return object;
      }
 catch (      IOException e) {
        throw buildDeserializeError(e.getMessage(),e);
      }
    }
  }
}
