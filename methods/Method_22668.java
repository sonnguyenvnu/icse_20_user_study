static public int getIndex(String what){
  Integer entry=platformIndices.get(what);
  return (entry == null) ? -1 : entry.intValue();
}
