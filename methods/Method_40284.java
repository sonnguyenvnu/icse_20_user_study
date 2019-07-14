@Override public Type resolve(Scope outer,int tag) throws Exception {
  this.defaultTypes=resolveAndConstructList(defaults,outer,tag);
  FunType cl=new FunType(this,outer.getForwarding());
  cl.getTable().setParent(outer);
  cl.getTable().setPath(outer.extendPath(getName().getId()));
  NameBinder.bind(outer,getName(),cl,Binding.Kind.FUNCTION,tag);
  cl.setDefaultTypes(resolveAndConstructList(defaults,outer,tag));
  Indexer.idx.addUncalled(cl);
  return cl;
}
