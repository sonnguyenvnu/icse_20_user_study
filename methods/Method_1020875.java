@Override public AssociationEmbedded build(Map params){
  Map temp=map();
  temp.putAll(params);
  Document child=(Document)staticMethod(kclass,"create9",temp);
  child._parent=document;
  child.associationEmbeddedName=name;
  children.add(child);
  List childAttr=(List)document.attributes().get(name);
  if (isEmpty(childAttr))   document.attributes().put(name,list());
  childAttr=(List)document.attributes().get(name);
  childAttr.add(temp);
  return this;
}
