@NotNull public static Type apply(@NotNull FunType func,@Nullable List<Type> pos,Map<String,Type> hash,Type kw,Type star,@Nullable Node call){
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
  if (func.selfType != null) {
    pTypes.add(func.selfType);
  }
 else {
    if (func.cls != null) {
      pTypes.add(func.cls.getCanon());
    }
  }
  if (pos != null) {
    pTypes.addAll(pos);
  }
  bindMethodAttrs(func);
  State funcTable=new State(func.env,State.StateType.FUNCTION);
  if (func.table.parent != null) {
    funcTable.setPath(func.table.parent.extendPath(func.func.name.id));
  }
 else {
    funcTable.setPath(func.func.name.id);
  }
  Type fromType=bindParams(call,func.func,funcTable,func.func.args,func.func.vararg,func.func.kwarg,pTypes,func.defaultTypes,hash,kw,star);
  Type cachedTo=func.getMapping(fromType);
  if (cachedTo != null) {
    func.setSelfType(null);
    return cachedTo;
  }
 else {
    Type toType=transformExpr(func.func.body,funcTable);
    if (missingReturn(toType)) {
      Analyzer.self.putProblem(func.func.name,"Function not always return a value");
      if (call != null) {
        Analyzer.self.putProblem(call,"Call not always return a value");
      }
    }
    toType=UnionType.remove(toType,Type.CONT);
    func.addMapping(fromType,toType);
    func.setSelfType(null);
    return toType;
  }
}
