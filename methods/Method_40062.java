static void addReadOnlyAttr(@NotNull FunType fun,String name,@NotNull Type type,Binding.Kind kind){
  Node loc=Builtins.newDataModelUrl("the-standard-type-hierarchy");
  Binding b=new Binding(name,loc,type,kind);
  fun.table.update(name,b);
  b.markSynthetic();
  b.markStatic();
}
