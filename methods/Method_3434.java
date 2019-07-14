@Override public int idOf(String string){
  Integer id=stringIdMap.get(string);
  if (id == null)   id=-1;
  return id;
}
