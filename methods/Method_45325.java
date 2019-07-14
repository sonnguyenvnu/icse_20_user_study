public static Map<String,String[]> simpleValueToArray(final Map<String,String> map){
  ImmutableMap.Builder<String,String[]> builder=ImmutableMap.builder();
  for (  Map.Entry<String,String> entry : map.entrySet()) {
    builder.put(entry.getKey(),new String[]{entry.getValue()});
  }
  return builder.build();
}
