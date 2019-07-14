public static Type apply(FunType func,List<Type> aTypes,Map<String,Type> kTypes,Type kwargsType,Type starargsType,Node call,int tag) throws Exception {
  Indexer.idx.removeUncalled(func);
  if (func.getFunc() == null) {
    return Indexer.idx.builtins.unknown;
  }
 else   if (call != null && Indexer.idx.inStack(call)) {
    func.setSelfType(null);
    return Indexer.idx.builtins.unknown;
  }
  if (call != null) {
    Indexer.idx.pushStack(call);
  }
  List<Type> argTypeList=new ArrayList<Type>();
  if (func.getSelfType() != null) {
    argTypeList.add(func.getSelfType());
  }
 else   if (func.getCls() != null) {
    argTypeList.add(func.getCls().getCanon());
  }
  if (aTypes != null) {
    argTypeList.addAll(aTypes);
  }
  bindMethodAttrs(func,tag);
  Scope funcTable=new Scope(func.getEnv(),Scope.ScopeType.FUNCTION);
  if (func.getTable().getParent() != null) {
    funcTable.setPath(func.getTable().getParent().extendPath(func.func.name.id));
  }
 else {
    funcTable.setPath(func.func.name.id);
  }
  Type fromType=bindParams(call,funcTable,func.func.args,func.func.varargs,func.func.kwargs,argTypeList,func.defaultTypes,kTypes,kwargsType,starargsType,tag);
  Type cachedTo=func.getMapping(fromType);
  if (cachedTo != null) {
    func.setSelfType(null);
    return cachedTo;
  }
 else {
    Type toType=resolveExpr(func.func.body,funcTable,tag);
    if (missingReturn(toType)) {
      Indexer.idx.putProblem(func.func.name,"Function not always return a value");
      Indexer.idx.putProblem(call,"Call not always return a value");
    }
    func.setMapping(fromType,toType);
    func.setSelfType(null);
    return toType;
  }
}
