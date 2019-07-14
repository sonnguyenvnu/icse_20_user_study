public static Type resolveExpr(Node n,Scope s,int tag) throws Exception {
  Type result=n.resolve(s,tag);
  if (result == null) {
    Indexer.idx.warn(n + " resolved to a null type");
    return n.setType(Indexer.idx.builtins.unknown);
  }
  return result;
}
