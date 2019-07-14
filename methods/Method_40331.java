private ModuleType getStartModule(Scope s) throws Exception {
  if (!isTop()) {
    return getPrevious().getType().asModuleType();
  }
  ModuleType start=null;
  Scope mtable=s.getGlobalTable();
  if (mtable != null) {
    start=Indexer.idx.loadModule(mtable.getPath());
    if (start != null) {
      return start;
    }
  }
  String dir=new File(getFile()).getParent();
  if (dir == null) {
    Indexer.idx.warn("Unable to find parent dir for " + getFile());
    return null;
  }
  return Indexer.idx.loadModule(dir);
}
