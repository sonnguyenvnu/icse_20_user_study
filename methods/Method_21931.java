private Map<String,Object> getQueryParameters(final UriInfo info,final String[] params){
  final Map<String,Object> result=new HashMap<>();
  for (  String each : params) {
    if (!Strings.isNullOrEmpty(info.getQueryParameters().getFirst(each))) {
      result.put(each,info.getQueryParameters().getFirst(each));
    }
  }
  return result;
}
