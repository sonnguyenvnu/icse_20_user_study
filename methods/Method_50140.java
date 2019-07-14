protected ImmutableList<GraphQLFieldDefinition> serviceToFields(Class<?> client,ImmutableList<String> methodWhitelist){
  return getMethods(client,methodWhitelist).map(methodWrapper -> {
    try {
      methodWrapper.setAccessible(true);
      ParameterizedType callable=(ParameterizedType)methodWrapper.getGenericReturnType();
      GraphQLOutputType responseType=getReturnType(callable);
      Class<? extends Message> requestMessageClass=(Class<? extends Message>)callable.getActualTypeArguments()[0];
      Descriptors.Descriptor requestDescriptor=(Descriptors.Descriptor)requestMessageClass.getMethod("getDescriptor").invoke(null);
      Message requestMessage=((Message.Builder)requestMessageClass.getMethod("newBuilder").invoke(null)).buildPartial();
      Provider<?> service=getProvider(client);
      GqlInputConverter inputConverter=GqlInputConverter.newBuilder().add(requestDescriptor.getFile()).build();
      DataFetcher dataFetcher=(      DataFetchingEnvironment env) -> {
        Message input=inputConverter.createProtoBuf(requestDescriptor,requestMessage.toBuilder(),env.getArgument("input"));
        try {
          Object callableInstance=methodWrapper.invoke(service.get());
          Method method=callableInstance.getClass().getMethod("futureCall",Object.class);
          method.setAccessible(true);
          Object[] methodParameterValues=new Object[]{input};
          return method.invoke(callableInstance,methodParameterValues);
        }
 catch (        Exception e) {
          throw new RuntimeException(e);
        }
      }
;
      return GraphQLFieldDefinition.newFieldDefinition().name(transformName(methodWrapper.getName())).argument(GqlInputConverter.createArgument(requestDescriptor,"input")).type(responseType).dataFetcher(dataFetcher).build();
    }
 catch (    Exception e) {
      throw new RuntimeException(e);
    }
  }
).collect(ImmutableList.toImmutableList());
}
