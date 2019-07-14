public void insert(String id,@NotNull Node node,@NotNull Type type,Binding.Kind kind){
  Binding b=new Binding(id,node,type,kind);
  if (type instanceof ModuleType) {
    b.setQname(type.asModuleType().qname);
  }
 else {
    b.setQname(extendPath(id));
  }
  update(id,b);
}
