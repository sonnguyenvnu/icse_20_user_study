@Override public Type resolve(Scope s,int tag) throws Exception {
  Binding b=s.lookup(id);
  if (b != null) {
    Indexer.idx.putLocation(this,b);
    return setType(b.getType());
  }
 else {
    Indexer.idx.putProblem(this,"unbound variable " + getId());
    Type t=Indexer.idx.builtins.unknown;
    t.getTable().setPath(s.extendPath(getId()));
    return t;
  }
}
