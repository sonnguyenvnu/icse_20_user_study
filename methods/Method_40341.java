private Type getSubscript(Type vt,Type st,Scope s,int tag) throws Exception {
  if (vt.isUnknownType()) {
    return Indexer.idx.builtins.unknown;
  }
 else   if (vt.isListType()) {
    return getListSubscript(vt,st,s,tag);
  }
 else   if (vt.isTupleType()) {
    return getListSubscript(vt.asTupleType().toListType(),st,s,tag);
  }
 else   if (vt.isDictType()) {
    ListType nl=new ListType(vt.asDictType().valueType);
    return getListSubscript(nl,st,s,tag);
  }
 else   if (vt.isStrType()) {
    if (st == null || st.isListType() || st.isNumType()) {
      return vt;
    }
 else {
      addWarning("Possible KeyError (wrong type for subscript)");
      return Indexer.idx.builtins.unknown;
    }
  }
 else {
    return Indexer.idx.builtins.unknown;
  }
}
