private static Map<String,Object> convertHttptrace(List<Map<String,Object>> traces){
  return singletonMap("traces",traces.stream().sequential().map(LegacyEndpointConverters::convertHttptrace).collect(toList()));
}
