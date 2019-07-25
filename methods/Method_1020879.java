@Override public AssociationEmbedded build(Map params){
  child=(Document)ReflectHelper.staticMethod(kclass,"create9",params);
  child._parent=document;
  child.associationEmbeddedName=name;
  return this;
}
