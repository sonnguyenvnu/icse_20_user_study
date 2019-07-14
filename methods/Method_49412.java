public Map<String,Object> boolMust(List<Map<String,Object>> queries){
  return queries.size() > 1 ? ImmutableMap.of("bool",ImmutableMap.of("must",queries)) : queries.get(0);
}
