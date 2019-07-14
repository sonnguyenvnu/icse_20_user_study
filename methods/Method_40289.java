@Override public Type resolve(Scope s,int tag) throws Exception {
  ModuleType mt=new ModuleType(Util.moduleNameFor(file),file,Indexer.idx.globaltable);
  s.put(file,new Url("file://" + file),mt,Binding.Kind.MODULE,tag);
  resolveExpr(body,mt.getTable(),tag);
  resolveExportedNames(mt);
  return mt;
}
