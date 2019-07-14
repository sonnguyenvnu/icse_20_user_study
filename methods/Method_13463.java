protected String[] resolveParameterTypes(MethodMetadata methodMetadata){
  List<MethodParameterMetadata> params=methodMetadata.getParams();
  String[] parameterTypes=new String[params.size()];
  for (  MethodParameterMetadata parameterMetadata : params) {
    int index=parameterMetadata.getIndex();
    String parameterType=parameterMetadata.getType();
    parameterTypes[index]=parameterType;
  }
  return parameterTypes;
}
