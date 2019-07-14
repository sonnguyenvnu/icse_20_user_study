@Nullable ModuleType getCachedModule(String file){
  Type t=moduleTable.lookupType(_.moduleQname(file));
  if (t == null) {
    return null;
  }
 else   if (t instanceof UnionType) {
    for (    Type tt : ((UnionType)t).types) {
      if (tt instanceof ModuleType) {
        return (ModuleType)tt;
      }
    }
    return null;
  }
 else   if (t instanceof ModuleType) {
    return (ModuleType)t;
  }
 else {
    return null;
  }
}
