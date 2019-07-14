/** 
 * If the module defines an  {@code __all__} variable, resolve referencesto each of the elements.
 */
private void resolveExportedNames(Type t) throws Exception {
  if (t.isUnionType()) {
    for (    Type u : t.asUnionType().getTypes()) {
      if (u.isModuleType()) {
        t=u.asModuleType();
        break;
      }
    }
  }
  for (  Str nstr : getExportedNameNodes(t)) {
    Binding b=t.getTable().lookupLocal(nstr.getStr());
    if (b != null) {
      Indexer.idx.putLocation(nstr,b);
    }
  }
}
