public static Map<String,String> arrayValueToSimple(final Map<String,String[]> map){
  ImmutableMap.Builder<String,String> builder=ImmutableMap.builder();
  for (  Map.Entry<String,String[]> entry : map.entrySet()) {
    builder.put(entry.getKey(),entry.getValue()[0]);
  }
  return builder.build();
}
