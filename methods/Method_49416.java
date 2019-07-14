public Map<String,Object> queryString(Object query){
  return ImmutableMap.of("query_string",ImmutableMap.of("query",query));
}
