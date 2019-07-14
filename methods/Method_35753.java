public static Map<String,QueryParameter> splitQuery(String query){
  if (query == null) {
    return Collections.emptyMap();
  }
  Iterable<String> pairs=Splitter.on('&').split(query);
  ImmutableListMultimap.Builder<String,String> builder=ImmutableListMultimap.builder();
  for (  String queryElement : pairs) {
    int firstEqualsIndex=queryElement.indexOf('=');
    if (firstEqualsIndex == -1) {
      builder.putAll(queryElement,"");
    }
 else {
      String key=queryElement.substring(0,firstEqualsIndex);
      String value=decode(queryElement.substring(firstEqualsIndex + 1));
      builder.putAll(key,value);
    }
  }
  return Maps.transformEntries(builder.build().asMap(),new Maps.EntryTransformer<String,Collection<String>,QueryParameter>(){
    public QueryParameter transformEntry(    String key,    Collection<String> values){
      return new QueryParameter(key,ImmutableList.copyOf(values));
    }
  }
);
}
