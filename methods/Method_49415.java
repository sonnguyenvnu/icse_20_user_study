public Map<String,Object> boolFilter(Map<String,Object> query){
  return ImmutableMap.of("bool",ImmutableMap.of("must",MATCH_ALL,"filter",query));
}
