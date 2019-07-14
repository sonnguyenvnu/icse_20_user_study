public ModuleType lookupOrCreateModule(Node locator,String file){
  Type existing=Node.transformExpr(locator,this);
  if (existing.isModuleType()) {
    return existing.asModuleType();
  }
 else   if (locator instanceof Name) {
    List<Binding> bs=lookupAttr(((Name)locator).id);
    if (bs != null && bs.size() > 0 && bs.get(0).type.isModuleType()) {
      return bs.get(0).type.asModuleType();
    }
 else {
      ModuleType mt=new ModuleType(((Name)locator).id,file,this);
      this.insert(((Name)locator).id,locator,mt,Binding.Kind.MODULE);
      return mt;
    }
  }
 else   if (locator instanceof Attribute) {
    ModuleType mod=lookupOrCreateModule(((Attribute)locator).target,file);
    ModuleType mod2=new ModuleType(((Attribute)locator).attr.id,file,mod.table);
    mod.table.insert(((Attribute)locator).attr.id,((Attribute)locator).attr,mod2,Binding.Kind.MODULE);
    return mod2;
  }
 else {
    String name=locator.toString();
    return new ModuleType(name,null,this);
  }
}
