public static void bindIter(@NotNull State s,Node target,@NotNull Node iter,Binding.Kind kind){
  Type iterType=Node.transformExpr(iter,s);
  if (iterType.isListType()) {
    bind(s,target,iterType.asListType().eltType,kind);
  }
 else   if (iterType.isTupleType()) {
    bind(s,target,iterType.asTupleType().toListType().eltType,kind);
  }
 else {
    List<Binding> ents=iterType.table.lookupAttr("__iter__");
    if (ents != null) {
      for (      Binding ent : ents) {
        if (ent == null || !ent.type.isFuncType()) {
          if (!iterType.isUnknownType()) {
            Analyzer.self.putProblem(iter,"not an iterable type: " + iterType);
          }
          bind(s,target,Type.UNKNOWN,kind);
        }
 else {
          bind(s,target,ent.type.asFuncType().getReturnType(),kind);
        }
      }
    }
 else {
      bind(s,target,Type.UNKNOWN,kind);
    }
  }
}
