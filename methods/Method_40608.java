@NotNull public static Type apply(@NotNull FunType func,@Nullable List<Type> pos,Map<String,Type> hash,Type kw,Type star,Type block,@Nullable Node call){
  Analyzer.self.removeUncalled(func);
  if (func.func != null && !func.func.called) {
    Analyzer.self.nCalled++;
    func.func.called=true;
  }
  if (func.func == null) {
    return func.getReturnType();
  }
 else   if (call != null && Analyzer.self.inStack(call)) {
    func.setSelfType(null);
    return Type.UNKNOWN;
  }
  if (call != null) {
    Analyzer.self.pushStack(call);
  }
  List<Type> pTypes=new ArrayList<>();
  if (pos != null) {
    pTypes.addAll(pos);
  }
  State funcTable=new State(func.env,State.StateType.FUNCTION);
  if (func.table.parent != null) {
    funcTable.setPath(func.table.parent.extendPath(func.func.name.id,"."));
  }
 else {
    funcTable.setPath(func.func.name.id);
  }
  if (func.selfType != null) {
    Binder.bind(funcTable,new Name(Constants.INSTNAME),func.selfType,SCOPE);
  }
 else   if (func.cls != null) {
    Binder.bind(funcTable,new Name(Constants.INSTNAME),func.cls.getCanon(),SCOPE);
  }
  Type fromType=bindParams(call,func.func,funcTable,func.func.args,func.func.vararg,func.func.kwarg,pTypes,func.defaultTypes,hash,kw,star,block);
  Type cachedTo=func.getMapping(fromType);
  if (cachedTo != null && !(call != null && (call instanceof Call) && ((Call)call).isSuperCall())) {
    func.setSelfType(null);
    return cachedTo;
  }
 else {
    Type toType;
    if (func.isClassMethod) {
      boolean wasStatic=Analyzer.self.staticContext;
      Analyzer.self.setStaticContext(true);
      toType=transformExpr(func.func.body,funcTable);
      Analyzer.self.setStaticContext(wasStatic);
    }
 else {
      toType=transformExpr(func.func.body,funcTable);
    }
    if (missingReturn(toType)) {
      Analyzer.self.putProblem(func.func.locator,"Function not always return a value");
      if (call != null) {
        Analyzer.self.putProblem(call,"Call not always return a value");
      }
    }
    func.addMapping(fromType,toType);
    func.setSelfType(null);
    return toType;
  }
}
