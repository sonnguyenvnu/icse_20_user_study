private Map<String,Integer> buildParamNameToIndex(List<MethodParameterMetadata> params){
  Map<String,Integer> paramNameToIndex=new LinkedHashMap<>();
  for (  MethodParameterMetadata param : params) {
    paramNameToIndex.put(param.getName(),param.getIndex());
  }
  return paramNameToIndex;
}
