@Override public int idOf(String string){
  Integer id=featureIdMap.get(string);
  if (id == null) {
    id=featureIdMap.size();
    featureIdMap.put(string,id);
  }
  return id;
}
