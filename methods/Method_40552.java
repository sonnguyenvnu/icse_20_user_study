public ModuleType asModuleType(){
  if (this.isUnionType()) {
    for (    Type t : this.asUnionType().getTypes()) {
      if (t.isModuleType()) {
        return t.asModuleType();
      }
    }
    return null;
  }
 else   if (this.isModuleType()) {
    return (ModuleType)this;
  }
 else {
    return null;
  }
}
