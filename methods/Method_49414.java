public Map<String,Object> boolShould(List<Map<String,Object>> queries){
  return ImmutableMap.of("bool",ImmutableMap.of("should",queries));
}
