public static Binding bindName(Scope s,Name name,Type rvalue,Binding.Kind kind,int tag) throws Exception {
  Binding b;
  if (s.isGlobalName(name.getId())) {
    b=s.getGlobalTable().put(name.getId(),name,rvalue,kind,tag);
    Indexer.idx.putLocation(name,b);
  }
 else {
    b=s.put(name.getId(),name,rvalue,kind,tag);
  }
  Type nameType=b.getType();
  if (nameType.isUnknownType()) {
    nameType.getTable().setPath(b.getQname());
  }
  return b;
}
