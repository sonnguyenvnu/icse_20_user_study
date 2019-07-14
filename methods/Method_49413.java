public Map<String,Object> boolMustNot(Map<String,Object> query){
  return ImmutableMap.of("bool",ImmutableMap.of("must_not",query));
}
