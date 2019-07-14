protected ImmutableList<GraphQLFieldDefinition> serviceToFields(Class<?> client,ImmutableList<String> methodWhitelist){
  return getMethods(client,methodWhitelist).map(method -> {
    try {
      method.setAccessible(true);
      ParameterizedType returnType=(ParameterizedType)method.getGenericReturnType();
      GraphQLOutputType responseType=getReturnType(returnType);
      Class<? extends Message> requestMessageClass=(Class<? extends Message>)method.getParameterTypes()[0];
      Descriptors.Descriptor requestDescriptor=(Descriptors.Descriptor)requestMessageClass.getMethod("getDescriptor").invoke(null);
      Message requestMessage=((Message.Builder)requestMessageClass.getMethod("newBuilder").invoke(null)).buildPartial();
      Provider<?> service=getProvider(client);
      GqlInputConverter inputConverter=GqlInputConverter.newBuilder().add(requestDescriptor.getFile()).build();
      DataFetcher dataFetcher=(      DataFetchingEnvironment env) -> {
        Message input=inputConverter.createProtoBuf(requestDescriptor,requestMessage.toBuilder(),env.getArgument("input"));
        try {
          Object[] methodParameterValues=new Object[]{input};
          return method.invoke(service.get(),methodParameterValues);
        }
 catch (        Exception e) {
          throw new RuntimeException(e);
        }
      }
;
      return GraphQLFieldDefinition.newFieldDefinition().name(method.getName()).argument(GqlInputConverter.createArgument(requestDescriptor,"input")).type(responseType).dataFetcher(dataFetcher).build();
    }
 catch (    Exception e) {
      throw new RuntimeException(e);
    }
  }
).collect(ImmutableList.toImmutableList());
}
