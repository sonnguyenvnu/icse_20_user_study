public static SerializeFilter[] parseFilter(ResponseMessage<?> responseMessage){
  List<SerializeFilter> filters=new ArrayList<>();
  if (responseMessage.getIncludes() != null) {
    for (    Map.Entry<Class<?>,Set<String>> classSetEntry : responseMessage.getIncludes().entrySet()) {
      SimplePropertyPreFilter filter=new SimplePropertyPreFilter(classSetEntry.getKey());
      filter.getIncludes().addAll(classSetEntry.getValue());
      filters.add(filter);
    }
  }
  if (responseMessage.getExcludes() != null) {
    for (    Map.Entry<Class<?>,Set<String>> classSetEntry : responseMessage.getExcludes().entrySet()) {
      SimplePropertyPreFilter filter=new SimplePropertyPreFilter(classSetEntry.getKey());
      filter.getExcludes().addAll(classSetEntry.getValue());
      filters.add(filter);
    }
  }
  PropertyFilter responseMessageFilter=(object,name,value) -> !(object instanceof ResponseMessage) || value != null;
  filters.add(responseMessageFilter);
  return filters.toArray(new SerializeFilter[filters.size()]);
}
