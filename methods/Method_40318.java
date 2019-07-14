@Override public Type resolve(Scope s,int tag) throws Exception {
  if (n instanceof PyFloat) {
    return Indexer.idx.builtins.BaseFloat;
  }
 else   if (n instanceof PyComplex) {
    return Indexer.idx.builtins.BaseComplex;
  }
 else {
    return Indexer.idx.builtins.BaseNum;
  }
}
