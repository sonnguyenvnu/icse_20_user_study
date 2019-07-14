public static boolean isValidIdMap(Map<?,Integer> map){
  if (map == null)   return false;
  if (map.isEmpty())   return true;
  int size=map.size();
  Set<Integer> ids=new HashSet<>(size);
  for (  Integer id : map.values()) {
    if (id >= size || id < 0)     return false;
    if (!ids.add(id))     return false;
  }
  return true;
}
