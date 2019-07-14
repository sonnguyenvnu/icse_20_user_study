@Override public Type resolve(Scope s,int tag) throws Exception {
  Type vt=resolveExpr(value,s,tag);
  Type st=slice == null ? null : resolveExpr(slice,s,tag);
  if (vt.isUnionType()) {
    Type retType=Indexer.idx.builtins.unknown;
    for (    Type t : vt.asUnionType().getTypes()) {
      retType=UnionType.union(retType,getSubscript(t,st,s,tag));
    }
    return retType;
  }
 else {
    return getSubscript(vt,st,s,tag);
  }
}
