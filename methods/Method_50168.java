private GraphQLOutputType getReturnType(Method method) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
  if (!(method.getGenericReturnType() instanceof ParameterizedType)) {
    Class<?> returnType=method.getReturnType();
    if (Message.class.isAssignableFrom(returnType)) {
      @SuppressWarnings("unchecked") Class<? extends Message> responseClass=(Class<? extends Message>)method.getReturnType();
      Descriptor responseDescriptor=(Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
      referencedDescriptors.add(responseDescriptor);
      return ProtoToGql.getReference(responseDescriptor);
    }
    if (javaTypeToScalarMap.containsKey(returnType)) {
      return javaTypeToScalarMap.get(returnType);
    }
    throw new RuntimeException("Unknown scalar type: " + returnType.getTypeName());
  }
  ParameterizedType genericReturnType=(ParameterizedType)method.getGenericReturnType();
  java.lang.reflect.Type genericTypeValue=genericReturnType.getActualTypeArguments()[0];
  if (genericTypeValue instanceof ParameterizedType) {
    java.lang.reflect.Type listElType=((ParameterizedType)genericTypeValue).getActualTypeArguments()[0];
    @SuppressWarnings("unchecked") Class<? extends Message> responseClass=(Class<? extends Message>)listElType;
    Descriptor responseDescriptor=(Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
    referencedDescriptors.add(responseDescriptor);
    return new GraphQLList(new GraphQLNonNull(ProtoToGql.getReference(responseDescriptor)));
  }
  if (ImmutableList.class.isAssignableFrom((Class<?>)genericReturnType.getRawType())) {
    @SuppressWarnings("unchecked") Class<? extends Message> responseClass=(Class<? extends Message>)genericTypeValue;
    Descriptor responseDescriptor=(Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
    referencedDescriptors.add(responseDescriptor);
    return new GraphQLList(new GraphQLNonNull(ProtoToGql.getReference(responseDescriptor)));
  }
  @SuppressWarnings("unchecked") Class<? extends Message> responseClass=(Class<? extends Message>)genericReturnType.getActualTypeArguments()[0];
  Descriptor responseDescriptor=(Descriptor)responseClass.getMethod("getDescriptor").invoke(null);
  referencedDescriptors.add(responseDescriptor);
  return ProtoToGql.getReference(responseDescriptor);
}
