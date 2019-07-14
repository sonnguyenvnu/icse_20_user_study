@Override public Map<String,Object> getSubset(ConfigNamespace umbrella,String... umbrellaElements){
  ImmutableMap.Builder<String,Object> b=ImmutableMap.builder();
  Map<String,Object> fm=first.getSubset(umbrella,umbrellaElements);
  Map<String,Object> sm=second.getSubset(umbrella,umbrellaElements);
  b.putAll(first.getSubset(umbrella,umbrellaElements));
  for (  Map.Entry<String,Object> secondEntry : sm.entrySet()) {
    if (!fm.containsKey(secondEntry.getKey())) {
      b.put(secondEntry);
    }
  }
  return b.build();
}
