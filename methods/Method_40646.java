@NotNull @Override public Type transform(State s){
  Type vt=transformExpr(value,s);
  Type st=slice == null ? null : transformExpr(slice,s);
  if (vt.isUnionType()) {
    Type retType=Type.UNKNOWN;
    for (    Type t : vt.asUnionType().types) {
      retType=UnionType.union(retType,getSubscript(t,st,s));
    }
    return retType;
  }
 else {
    return getSubscript(vt,st,s);
  }
}
