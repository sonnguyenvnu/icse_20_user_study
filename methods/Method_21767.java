public static boolean clearEmptyPaths(Map<String,Object> map){
  if (map.size() == 0) {
    return true;
  }
  Set<String> keysToDelete=new HashSet<>();
  for (  Map.Entry<String,Object> entry : map.entrySet()) {
    Object value=entry.getValue();
    if (Map.class.isAssignableFrom(value.getClass())) {
      if (clearEmptyPaths((Map<String,Object>)value)) {
        keysToDelete.add(entry.getKey());
      }
    }
  }
  if (keysToDelete.size() != 0) {
    if (map.size() == keysToDelete.size()) {
      map.clear();
      return true;
    }
    for (    String key : keysToDelete) {
      map.remove(key);
      return false;
    }
  }
  return false;
}
