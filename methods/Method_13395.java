@Override protected boolean matchName(HttpRequest request){
  MultiValueMap<String,String> parametersMap=getParameters(request);
  return parametersMap.containsKey(this.name);
}
