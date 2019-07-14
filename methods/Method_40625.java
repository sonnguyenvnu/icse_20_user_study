@NotNull @Override public Type transform(@NotNull State s){
  ModuleType mt=s.lookupOrCreateModule(locator,file);
  mt.table.insert(Constants.SELFNAME,name,mt,Binding.Kind.SCOPE);
  transformExpr(body,mt.table);
  return mt;
}
