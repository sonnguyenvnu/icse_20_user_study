public static Map<String,String[]> iterableValueToArray(final Map<String,Iterable<String>> map){
  ImmutableMap.Builder<String,String[]> builder=ImmutableMap.builder();
  for (  Map.Entry<String,Iterable<String>> entry : map.entrySet()) {
    Iterable<String> value=entry.getValue();
    builder.put(entry.getKey(),Iterables.toArray(value,String.class));
  }
  return builder.build();
}
