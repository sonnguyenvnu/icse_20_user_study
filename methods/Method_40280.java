private void importStar(Scope s,ModuleType mt) throws Exception {
  if (mt == null || mt.getFile() == null) {
    return;
  }
  Module mod=Indexer.idx.getAstForFile(mt.getFile());
  if (mod == null) {
    return;
  }
  List<String> names=mod.getExportedNames(mt);
  if (!names.isEmpty()) {
    for (    String name : names) {
      Binding nb=mt.getTable().lookupLocal(name);
      if (nb != null) {
        s.put(name,nb);
      }
    }
  }
 else {
    for (    Entry<String,Binding> e : mt.getTable().entrySet()) {
      if (!e.getKey().startsWith("_")) {
        s.put(e.getKey(),e.getValue());
      }
    }
  }
}
