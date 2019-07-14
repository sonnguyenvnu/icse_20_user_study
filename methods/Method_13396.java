@Override protected boolean matchValue(HttpRequest request){
  MultiValueMap<String,String> parametersMap=getParameters(request);
  String parameterValue=parametersMap.getFirst(this.name);
  return ObjectUtils.nullSafeEquals(this.value,parameterValue);
}
