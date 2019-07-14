/** 
 * First mark all the global names then resolve each statement in the sequence.  Notice that we don't distinguish class and function definitions here.  Their statements will return None type and bind the names themselves in the scope. If the sequence contains escaping control-flow, None type will appear in the return type. This can be used to generate warnings such as "This function may not return a value."
 */
@Override public Type resolve(Scope scope,int tag) throws Exception {
  for (  Node n : seq) {
    if (n.isGlobal()) {
      for (      Name name : n.asGlobal().getNames()) {
        scope.addGlobalName(name.getId());
        Binding nb=scope.lookup(name.getId());
        if (nb != null) {
          Indexer.idx.putLocation(name,nb);
        }
      }
    }
  }
  boolean returned=false;
  Type retType=Indexer.idx.builtins.unknown;
  for (  Node n : seq) {
    Type t=resolveExpr(n,scope,tag);
    if (!returned) {
      retType=UnionType.union(retType,t);
      if (!UnionType.contains(t,Indexer.idx.builtins.Cont)) {
        returned=true;
        retType=UnionType.remove(retType,Indexer.idx.builtins.Cont);
      }
    }
 else     if (scope.getScopeType() != Scope.ScopeType.GLOBAL && scope.getScopeType() != Scope.ScopeType.MODULE) {
      Indexer.idx.putProblem(n,"unreachable code");
    }
  }
  return retType;
}
