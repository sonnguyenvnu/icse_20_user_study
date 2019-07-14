/** 
 * Resolves and loads the module named by this qname.
 * @return the module represented by the qname up to this point.
 */
@Override public Type resolve(Scope s,int tag) throws Exception {
  setType(name.setType(Indexer.idx.builtins.unknown));
  if (isUnqualified()) {
    ModuleType mt=Indexer.idx.loadModule(name.getId());
    if (mt != null) {
      return setType(name.setType(mt));
    }
  }
 else {
    ModuleType mt=Indexer.idx.getBuiltinModule(thisQname());
    if (mt != null) {
      setType(name.setType(mt));
      if (next != null)       resolveExpr(next,s,tag);
      return mt;
    }
  }
  return resolveInFilesystem(s,tag);
}
