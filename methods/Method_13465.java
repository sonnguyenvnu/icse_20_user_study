protected Object[] resolveParameters(RestMethodMetadata dubboRestMethodMetadata,RestMethodMetadata clientRestMethodMetadata,Object[] arguments){
  MethodMetadata dubboMethodMetadata=dubboRestMethodMetadata.getMethod();
  List<MethodParameterMetadata> params=dubboMethodMetadata.getParams();
  Object[] parameters=new Object[params.size()];
  for (  MethodParameterMetadata parameterMetadata : params) {
    int index=parameterMetadata.getIndex();
    for (    DubboGenericServiceParameterResolver resolver : resolvers) {
      Object parameter=resolver.resolve(dubboRestMethodMetadata,parameterMetadata,clientRestMethodMetadata,arguments);
      if (parameter != null) {
        parameters[index]=parameter;
        break;
      }
    }
  }
  return parameters;
}
