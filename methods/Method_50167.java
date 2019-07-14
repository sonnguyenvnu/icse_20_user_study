private GraphQLFieldDefinition methodToFieldDefinition(Object module,Method method,String name,@Nullable String fullName,@Nullable Descriptor descriptor){
  method.setAccessible(true);
  try {
    ImmutableList<MethodMetadata> methodParameters=getMethodMetadata(method,descriptor);
    DataFetcher<?> dataFetcher=(    DataFetchingEnvironment environment) -> {
      Object[] methodParameterValues=new Object[methodParameters.size()];
      for (int i=0; i < methodParameters.size(); i++) {
        methodParameterValues[i]=methodParameters.get(i).getParameterValue(environment);
      }
      try {
        return method.invoke(module,methodParameterValues);
      }
 catch (      InvocationTargetException e) {
        Throwable cause=e.getCause();
        if (cause instanceof RuntimeException) {
          throw (RuntimeException)cause;
        }
        throw new RuntimeException(cause);
      }
catch (      Exception e) {
        throw new RuntimeException(e);
      }
    }
;
    GraphQLOutputType returnType=getReturnType(method);
    GraphQLFieldDefinition.Builder fieldDef=GraphQLFieldDefinition.newFieldDefinition();
    fieldDef.type(returnType);
    fieldDef.name(name);
    fieldDef.description(DescriptorSet.COMMENTS.get(fullName));
    for (    MethodMetadata methodMetadata : methodParameters) {
      if (methodMetadata.hasArgument()) {
        fieldDef.argument(methodMetadata.argument());
      }
    }
    fieldDef.dataFetcher(dataFetcher);
    return fieldDef.build();
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
