@NotNull @Override public Type transform(@NotNull State s){
  if (locator != null) {
    Type existing=transformExpr(locator,s);
    if (existing instanceof ClassType) {
      if (body != null) {
        boolean wasStatic=Analyzer.self.staticContext;
        Analyzer.self.setStaticContext(true);
        transformExpr(body,existing.table);
        Analyzer.self.setStaticContext(wasStatic);
      }
      return Type.CONT;
    }
  }
  ClassType classType=new ClassType(name.id,s);
  classType.table.setParent(s);
  if (base != null) {
    Type baseType=transformExpr(base,s);
    if (baseType.isClassType()) {
      classType.addSuper(baseType);
    }
 else {
      Analyzer.self.putProblem(base,base + " is not a class");
    }
  }
  Binder.bind(s,name,classType,Binding.Kind.CLASS);
  classType.table.insert(Constants.SELFNAME,name,classType,Binding.Kind.SCOPE);
  if (body != null) {
    transformExpr(body,classType.table);
  }
  return Type.CONT;
}
