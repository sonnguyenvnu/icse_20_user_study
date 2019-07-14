private GraphQLOutputType getReturnType(ParameterizedType parameterizedType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
  Class<? extends Message> responseClass=(Class<? extends Message>)parameterizedType.getActualTypeArguments()[1];
  Descriptors.Descriptor responseDescriptor=(Descriptors.Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
  addExtraType(responseDescriptor);
  return ProtoToGql.getReference(responseDescriptor);
}
