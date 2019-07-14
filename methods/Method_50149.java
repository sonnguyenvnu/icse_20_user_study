/** 
 * Returns a GraphQLOutputType for type T for an input of ListenableFuture<T>. 
 */
private GraphQLOutputType getReturnType(ParameterizedType parameterizedType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
  Class<? extends Message> responseClass=(Class<? extends Message>)parameterizedType.getActualTypeArguments()[0];
  Descriptors.Descriptor responseDescriptor=(Descriptors.Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
  addExtraType(responseDescriptor);
  return ProtoToGql.getReference(responseDescriptor);
}
