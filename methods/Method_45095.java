private static Function<Map.Entry<String,String[]>,Iterable<Map.Entry<String,String>>> toMapEntries(){
  return new Function<Map.Entry<String,String[]>,Iterable<Map.Entry<String,String>>>(){
    @Override public Iterable<Map.Entry<String,String>> apply(    final Map.Entry<String,String[]> input){
      String key=input.getKey();
      ImmutableList.Builder<Map.Entry<String,String>> builder=ImmutableList.builder();
      for (      String value : input.getValue()) {
        builder.add(Maps.immutableEntry(key,value));
      }
      return builder.build();
    }
  }
;
}
