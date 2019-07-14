ModuleType getCachedModule(String file){
  Type t=moduleTable.lookupType(file);
  if (t == null) {
    return null;
  }
 else   if (t.isUnionType()) {
    for (    Type tt : t.asUnionType().getTypes()) {
      if (tt.isModuleType()) {
        return (ModuleType)tt;
      }
    }
    return null;
  }
 else   if (t.isModuleType()) {
    return (ModuleType)t;
  }
 else {
    return null;
  }
}
