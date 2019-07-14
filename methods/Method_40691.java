public void insert(String id,Node node,Type type,Binding.Kind kind){
  Binding b=new Binding(node,type,kind);
  if (type.isModuleType()) {
    b.setQname(type.asModuleType().qname);
  }
 else {
    if (type instanceof FunType) {
      if (id.endsWith(Constants.IDSEP + "class")) {
        b.setQname(extendPath(id,"."));
      }
 else {
        b.setQname(extendPath(id,"#"));
      }
    }
 else {
      b.setQname(extendPath(id,"::"));
    }
  }
  update(id,b);
}
