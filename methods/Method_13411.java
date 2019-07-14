private List<MethodParameterMetadata> initParameters(Method method){
  int parameterCount=method.getParameterCount();
  if (parameterCount < 1) {
    return Collections.emptyList();
  }
  List<MethodParameterMetadata> params=new ArrayList<>(parameterCount);
  Parameter[] parameters=method.getParameters();
  for (int i=0; i < parameterCount; i++) {
    Parameter parameter=parameters[i];
    MethodParameterMetadata param=toMethodParameterMetadata(i,parameter);
    params.add(param);
  }
  return params;
}
