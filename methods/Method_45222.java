private static ImmutableMap<String,String[]> toQueries(final QueryStringDecoder decoder){
  ImmutableMap.Builder<String,String[]> builder=ImmutableMap.builder();
  for (  Map.Entry<String,List<String>> entry : decoder.parameters().entrySet()) {
    List<String> value=entry.getValue();
    builder.put(entry.getKey(),value.toArray(new String[0]));
  }
  return builder.build();
}
