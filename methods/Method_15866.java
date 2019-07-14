protected Set<String> getStringListFromMap(Map<Class<?>,Set<String>> map,Class type){
  return map.computeIfAbsent(type,k -> new HashSet<>());
}
