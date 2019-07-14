public Map<String,Object> createTextMapping(String textAnalyzer){
  final ImmutableMap.Builder builder=ImmutableMap.builder().put(ES_TYPE_KEY,"text");
  return (textAnalyzer != null ? builder.put(ES_ANALYZER,textAnalyzer) : builder).build();
}
