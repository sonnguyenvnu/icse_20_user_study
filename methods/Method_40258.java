static Binding addReadOnlyAttr(FunType cl,String name,Type type,Binding.Kind kind,int tag){
  Binding b=cl.getTable().put(name,Builtins.newDataModelUrl("the-standard-type-hierarchy"),type,kind,tag);
  b.markSynthetic();
  b.markStatic();
  b.markReadOnly();
  return b;
}
