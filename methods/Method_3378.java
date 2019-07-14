@Override public int idOf(String string){
  Integer id=featureIdMap.get(string);
  if (id == null)   return -1;
  return id;
}
